package com.osivia.cns.proto.ldap;

import java.util.Map;

import javax.portlet.PortletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osivia.portal.api.directory.entity.DirectoryPerson;
import org.osivia.portal.api.login.IUserDatasModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osivia.cns.proto.ldap.dao.PersonneCNSDao;
import com.osivia.cns.proto.ldap.entite.PersonneCNS;

import fr.toutatice.outils.ldap.entity.Person;

@Service
public class Feeder implements IUserDatasModule {

    /** Logger. */
    protected static final Log logger = LogFactory.getLog(Feeder.class);
	
	private PortletContext portletContext;
	
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
		
        String uid = request.getUserPrincipal().getName();
        if (uid != null) {
        	
        	Person findUtilisateur = person.findUtilisateur(uid);
        	
        	logger.warn("Recherche correspondance pour "+uid+" trouvée : " + findUtilisateur.getDisplayName());
        	
        }
		
		// Recherche personne dans le LDAP auxiliaire
        PersonneCNS findByPrimaryKey = srcPersonneDao.findByPrimaryKey("achauvet");
        logger.warn("Recherche Annuaire source pourtrouvée : " + findByPrimaryKey.getDisplayName());
        
		// Si trouvé
		// Extraction groupes
		
		// Mapping groupe annuaire cible
		
		// Alimentation
		
		// Création du userworkspace
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	public PortletContext getPortletContext() {
		return portletContext;
	}

	public void setPortletContext(PortletContext portletContext) {
		this.portletContext = portletContext;
	}

	
}
