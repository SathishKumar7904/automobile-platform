export interface Brand { id: string; name: string; }
export interface VehicleModel { id: string; brandId: string; name: string; }
export interface VehicleVariant {
  id: string; modelId: string; name: string; bodyType: string;
  fuelType: string; transmission: string; price: number;
}
export interface Dealer {
  id: string; name: string; city: string; state: string;
  address: string; phone: string; email: string;
}
export type LeadStatus = 'NEW' | 'CONTACTED' | 'INTERESTED' | 'FOLLOW_UP' | 'CONVERTED' | 'CLOSED';
export interface Lead {
  id?: string; leadId?: string; enquiryId?: string; customerId: string;
  variantId: string; dealerId: string; status: LeadStatus;
  createdAt: string; updatedAt: string; message?: string;
}
export interface CustomerEnquiry {
  enquiryId: string; leadId: string; customerId: string; variantId: string;
  dealerId: string; message?: string; status: LeadStatus; submittedAt: string;
}
export interface StatusHistory {
  id: string; leadId: string; previousStatus?: LeadStatus; newStatus: LeadStatus;
  changedBy?: string; changedAt: string; reason?: string;
}
export interface LeadNote {
  id: string; leadId: string; content: string; createdBy?: string; createdAt: string;
}
export interface CreateEnquiryRequest { customerId: string; variantId: string; dealerId: string; message?: string; }
export interface UpdateLeadStatusRequest { status: LeadStatus; changedBy?: string; reason?: string; }
export interface CreateLeadNoteRequest { content: string; createdBy?: string; }
