#!/bin/sh
### ====================================================================== ###
##                                                                          ##
##  migration ldap pour cns                                    		    ##
##                                                                          ##
### ====================================================================== ###

LDIF_file=$1


cp ${LDIF_file} ${LDIF_file}.bak

sed -e 's/GEDPersonProfils/portalPersonProfile/g ; s/GEDPersonEntite/ou/g ; s/GEDDisplayName/portalDisplayName/g ; s/GEDProfilPeuplement/portalProfilePopulating/g ; s/GEDProfilType/portalProfileType/g ; s/GEDExplicitMembers/portalExplicitMember/g ; s/GEDExplicitManagers/portalExplicitManager/g ; s/GEDPerson/portalPerson/g ; s/GEDProfil/portalProfile/g ; s/groupOfNames/groupOfUniqueNames/g ; s/member:/uniqueMember:/g ; /^structuralObjectClass: inetOrgPerson/d' ${LDIF_file}.bak > ${LDIF_file}

#sed s/GEDPersonProfils/portalPersonProfile/g ${LDIF_file} > ${LDIF_file}
#sed s/GEDPersonEntite/ou/g ${LDIF_file} > ${LDIF_file}
#sed s/GEDDisplayName/portalDisplayName/g ${LDIF_file} > ${LDIF_file}
#sed s/GEDProfilPeuplement/portalProfilePopulating/g ${LDIF_file} > ${LDIF_file}
#sed s/GEDProfilType/portalProfileType/g ${LDIF_file} > ${LDIF_file}
#sed s/GEDExplicitMembers/portalExplicitMember/g ${LDIF_file} > ${LDIF_file}
#sed s/GEDExplicitManagers/portalExplicitManager/g ${LDIF_file} > ${LDIF_file}
#sed s/GEDPerson/portalPerson/g ${LDIF_file} > ${LDIF_file}
#sed s/GEDProfil/portalProfile/g ${LDIF_file} > ${LDIF_file}
#sed s/groupOfNames/groupOfUniqueNames/g ${LDIF_file} > ${LDIF_file}
#sed s/member:/uniqueMember:/g ${LDIF_file} > ${LDIF_file}
#sed /^structuralObjectClass: inetOrgPerson/d ${LDIF_file} > ${LDIF_file}
