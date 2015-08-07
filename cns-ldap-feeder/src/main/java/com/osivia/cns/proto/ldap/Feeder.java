package com.osivia.cns.proto.ldap;

import java.util.Map;

import javax.annotation.Resource;
import javax.portlet.PortletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osivia.portal.api.directory.entity.DirectoryPerson;
import org.osivia.portal.api.login.IUserDatasModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.osivia.cns.proto.ldap.dao.PersonneCNSDao;
import com.osivia.cns.proto.ldap.entite.PersonneCNS;

import fr.toutatice.outils.ldap.entity.Person;
import fr.toutatice.outils.ldap.entity.Profil;
import fr.toutatice.outils.ldap.exception.ToutaticeAnnuaireException;

@Service
public class Feeder implements IUserDatasModule {

    /** Logger. */
    protected static final Log logger = LogFactory.getLog("feeder");
	
	private PortletContext portletContext;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private Person person;
	
	@Autowired
	private Profil Profil;	
	
	@Autowired
	private PersonneCNSDao srcPersonneDao;	
	
	
	@Resource
	private Map<String, String> profilesMapping;
	
	@Override
	public void computeUserDatas(HttpServletRequest request,
			Map<String, Object> datas) {
		// Rien à faire ici
		
	}

	@Override
	public DirectoryPerson computeLoggedUser(HttpServletRequest request) {
		
		return null;
	}

	public PortletContext getPortletContext() {
		return portletContext;
	}

	public void setPortletContext(PortletContext portletContext) {
		this.portletContext = portletContext;
	}
		

	public DirectoryPerson computeUser(String username) {
		// Recherche personne dans le LDAP auxiliaire
        PersonneCNS pSource = srcPersonneDao.findByPrimaryKey(username);
        
        
        Person pDest = person.findUtilisateur(username);
        
        
		if (pSource != null) {
			
			
			if (pDest == null) {
				logger.info("Recherche Annuaire PHM pour "+username+" trouvée : " + pSource.getDisplayName());
				
				Person person2 = toPerson(pSource);
				try {
					
					logger.info("Création de la personne : " + pSource.getDisplayName());
					person2.create();
					
					// Association au membres communs.
					String dnProfilCommun = profilesMapping.get("commun");
					Profil profilCommun = Profil.findProfilByDn(dnProfilCommun);
					profilCommun.addMember(person2.getDn());
					
					logger.info("Ajout au groupe commun : " + profilCommun.getDn());
					
					profilCommun.updateProfil();
					
					person2.addProfil(dnProfilCommun);
					
					String entite = pSource.getEntite();
					if(StringUtils.isNotBlank(entite)) {
						String dnProfilSpecif = profilesMapping.get(StringUtils.upperCase(entite));
						if(StringUtils.isNotBlank(dnProfilSpecif)) {
							Profil profilSpecif = Profil.findProfilByDn(dnProfilSpecif);
							
							if(profilSpecif != null) {
								profilSpecif.addMember(person2.getDn());
								
								logger.info("Entite "+entite+" trouvée, Ajout au groupe spécifique : " + profilSpecif.getDn());
								
								profilSpecif.updateProfil();
							}
							else {
								logger.error("Entite  "+entite+" trouvée sans espace de rattachement");
							}
						}
						else {
							logger.error("Entite  "+entite+" trouvée sans espace de rattachement");
						}
					}
					else {
						logger.error("Entite non définie");
					}
					
					
				} catch (ToutaticeAnnuaireException e) {
					logger.error("Impossible de créer la personne "+pSource.getDisplayName());
					logger.error(e);
				}
			} else {

			}
		}
        
        
        return null;
	}

	private Person toPerson(PersonneCNS pSource) {
		
		Person p = context.getBean(Person.class);
		
		p.setUid(pSource.getUid());
		p.setCn(pSource.getCn());
		p.setDisplayName(pSource.getDisplayName());
		p.setGivenName(pSource.getGivenName());
		p.setEmail(pSource.getMail());
		p.setSn(pSource.getSn());
		if(StringUtils.isNotBlank(pSource.getEntite())) {
			p.setDivcod(StringUtils.upperCase(pSource.getEntite()));
		}
		
		return p;
		
	}

	
}
