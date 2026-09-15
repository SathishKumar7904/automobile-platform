CREATE TABLE enquiry.lead_status_history (
    id UUID PRIMARY KEY,
    lead_id UUID NOT NULL,
    previous_status VARCHAR(30),
    new_status VARCHAR(30) NOT NULL,
    changed_by UUID,
    changed_at TIMESTAMP WITH TIME ZONE NOT NULL,
    reason VARCHAR(2000),

    CONSTRAINT fk_lead_status_history_lead
        FOREIGN KEY (lead_id)
        REFERENCES enquiry.leads(id),

    CONSTRAINT ck_lead_status_history_new_status
        CHECK (
            new_status IN (
                'NEW',
                'CONTACTED',
                'INTERESTED',
                'FOLLOW_UP',
                'CONVERTED',
                'CLOSED'
            )
        ),

    CONSTRAINT ck_lead_status_history_previous_status
        CHECK (
            previous_status IS NULL
            OR previous_status IN (
                'NEW',
                'CONTACTED',
                'INTERESTED',
                'FOLLOW_UP',
                'CONVERTED',
                'CLOSED'
            )
        )
);

CREATE INDEX idx_lead_status_history_lead
    ON enquiry.lead_status_history(lead_id);

CREATE INDEX idx_lead_status_history_changed_at
    ON enquiry.lead_status_history(changed_at);


CREATE TABLE enquiry.lead_notes (
    id UUID PRIMARY KEY,
    lead_id UUID NOT NULL,
    content VARCHAR(2000) NOT NULL,
    created_by UUID,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_lead_notes_lead
        FOREIGN KEY (lead_id)
        REFERENCES enquiry.leads(id),

    CONSTRAINT ck_lead_notes_content_not_blank
        CHECK (length(trim(content)) > 0)
);

CREATE INDEX idx_lead_notes_lead
    ON enquiry.lead_notes(lead_id);

CREATE INDEX idx_lead_notes_created_at
    ON enquiry.lead_notes(created_at);