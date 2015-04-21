package com.osivia.cns.proto.ldap;

import java.util.Map;

import javax.portlet.PortletContext;
import javax.servlet.http.HttpServletRequest;

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
import fr.toutatice.outils.ldap.exception.ToutaticeAnnuaireException;

@Service
public class Feeder implements IUserDatasModule {

    /** Logger. */
    protected static final Log logger = LogFactory.getLog(Feeder.class);
	
	private PortletContext portletContext;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private Person person;
	
	@Autowired
	private PersonneCNSDao srcPersonneDao;	
	
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

	@Override
	public DirectoryPerson computeUser(String username) {
		// Recherche personne dans le LDAP auxiliaire
        PersonneCNS pSource = srcPersonneDao.findByPrimaryKey(username);
        logger.warn("Recherche Annuaire source pour "+username+" trouvée : " + pSource.getDisplayName());
        
        Person pDest = person.findUtilisateur(username);
        
        
		if (pSource != null) {
			if (pDest == null) {
				Person person2 = toPerson(pSource);
				try {
					person2.create();
				} catch (ToutaticeAnnuaireException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				logger.warn("Recherche Annuaire cible pour " + username
						+ " trouvée : " + pDest.getDisplayName());
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
		
		return p;
		
	}

	
}
