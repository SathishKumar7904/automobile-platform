CREATE SCHEMA IF NOT EXISTS enquiry;

CREATE TABLE enquiry.enquiries (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    variant_id UUID NOT NULL,
    dealer_id UUID NOT NULL,
    message VARCHAR(2000),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX idx_enquiries_customer ON enquiry.enquiries(customer_id);
CREATE INDEX idx_enquiries_dealer ON enquiry.enquiries(dealer_id);
CREATE INDEX idx_enquiries_variant ON enquiry.enquiries(variant_id);

CREATE TABLE enquiry.leads (
    id UUID PRIMARY KEY,
    enquiry_id UUID NOT NULL UNIQUE,
    customer_id UUID NOT NULL,
    variant_id UUID NOT NULL,
    dealer_id UUID NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_leads_enquiry FOREIGN KEY (enquiry_id) REFERENCES enquiry.enquiries(id),
    CONSTRAINT ck_leads_status CHECK (status IN ('NEW','CONTACTED','INTERESTED','FOLLOW_UP','CONVERTED','CLOSED'))
);

CREATE INDEX idx_leads_customer ON enquiry.leads(customer_id);
CREATE INDEX idx_leads_dealer ON enquiry.leads(dealer_id);
CREATE INDEX idx_leads_status ON enquiry.leads(status);
