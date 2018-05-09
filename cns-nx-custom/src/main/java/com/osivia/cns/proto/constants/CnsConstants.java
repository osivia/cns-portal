package com.osivia.cns.proto.constants;

import java.util.HashMap;
import java.util.Map;


/**
 * @author David Chevrier.
 *
 */ 
public interface CnsConstants {
    
    /** Metadata. */
    String CNS_SOURCE = "dc:source";
    String CNS_S_INFO_ASSOCIE = "cns:s_info_associe";
    String CNS_NATURE = "dc:nature";
    String CNS_SUBJECTS = "dc:subjects";
    String CNS_CYCLE_VIE = "cns:cycle_vie";
    
	/** CNS nature vocabulary. */
    String NATURE_VOCABULARY = "nature";
    /** CNS nature vocabulary schema. */
    String NATURE_VOCABULARY_SCHEMA = "l10nxvocabulary";
    
    /** CNS Direct validation. */
    String CNS_VALIDATION_WF = "cns-validation";
    /** CNS direct Validation task. */
    String CNS_VALIDATION_TASK = "direct-validation-task";
    /** CNS direct validation button. */
    String CNS_VALIDATION_BUTTON = "direct-validation";
    /** CNS cancel button. */
    String CNS_CANCEL_BUTTON = "cancel";
    
    /** Metadata (map task/document). */
    Map<String, String> METADATA = new HashMap<String, String>(5) {

        private static final long serialVersionUID = -7996841415431657806L;

        {
            put("cns.cycle_vie", CNS_CYCLE_VIE);
            put("dc.subjects", CNS_SUBJECTS);
            put("dc.nature", CNS_NATURE);
            put("cns.s_info_associe", CNS_S_INFO_ASSOCIE);
            put("dc.source", CNS_SOURCE);
        }
    };
    
    /** Forums domain's name. */
    String FORUMS_DOMAIN_NAME = "forums";

}
