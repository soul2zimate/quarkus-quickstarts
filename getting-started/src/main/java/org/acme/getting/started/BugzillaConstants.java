package org.acme.getting.started;

public class BugzillaConstants {
    static final String RESULT_INCLUDE_FIELDS = "include_fields";
    // Issue fields
    static final String ASSIGNEE = "assigned_to";
    static final String BLOCKS = "blocks";
    static final String COMMENT = "comment";
    static final String COMMENT_ID = "id";
    static final String COMMENT_IS_PRIVATE = "is_private";
    static final String COMMENT_BODY = "text";
    static final String COMMENT_BUG_ID = "bug_id";
    static final String COMPONENT = "component";
    static final String CREATION_TIME = "creation_time";
    static final String DEPENDS_ON = "depends_on";
    static final String EXTERNAL_URL = "external_bugs";
    static final String DESCRIPTION = "description";
    static final String ESTIMATED_TIME = "estimated_time";
    static final String FILTER_SHARER_ID = "sharer_id";
    static final String FLAG_ACK_DEVEL = "devel_ack";
    static final String FLAG_ACK_PM = "pm_ack";
    static final String FLAG_ACK_QA = "qa_ack";
    static final String FLAGS = "flags";
    static final String FLAG_NAME = "name";
    static final String FLAG_STATUS = "status";
    static final String HOURS_WORKED = "actual_time";
    static final String ID = "id";
    static final String ISSUE_IDS = "ids";
    static final String ISSUE_TYPE = "cf_type";
    static final String LAST_UPDATED = "last_change_time";
    static final String NAME = "name";
    static final String PRIVATE_COMMENT = "private";
    static final String PRODUCT = "product";
    static final String REPORTER = "creator";
    static final String SUMMARY = "summary";
    static final String STATUS = "status";
    static final String PRIORITY = "priority";
    static final String TARGET_MILESTONE = "target_milestone";
    static final String TARGET_RELEASE = "target_release";
    static final String UPDATE_FIELDS = "updates";
    static final String VERSION = "version";
    static final Object[] RESULT_FIELDS = { ASSIGNEE, BLOCKS, COMPONENT, CREATION_TIME, LAST_UPDATED, DEPENDS_ON, SUMMARY,
            DESCRIPTION, ESTIMATED_TIME, FLAGS, HOURS_WORKED, ID, ISSUE_TYPE, PRODUCT, REPORTER, STATUS, PRIORITY, TARGET_MILESTONE,
            TARGET_RELEASE, VERSION, EXTERNAL_URL };

    static final String RESULT_PERMISSIVE_SEARCH = "permissive";
    static final String METHOD_GET_BUG = "Bug.get";
    static final String RESULT_BUGS = "bugs";
}
