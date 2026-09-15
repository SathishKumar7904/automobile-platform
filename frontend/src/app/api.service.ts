import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Brand, VehicleModel, VehicleVariant, Dealer, CustomerEnquiry, Lead, StatusHistory, LeadNote, CreateEnquiryRequest, UpdateLeadStatusRequest, CreateLeadNoteRequest } from './models';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly http = inject(HttpClient);
  brands(): Observable<Brand[]> { return this.http.get<Brand[]>('/api/vehicles/brands'); }
  models(brandId: string): Observable<VehicleModel[]> { return this.http.get<VehicleModel[]>(`/api/vehicles/brands/${brandId}/models`); }
  variants(modelId: string): Observable<VehicleVariant[]> { return this.http.get<VehicleVariant[]>(`/api/vehicles/models/${modelId}/variants`); }
  variant(id: string): Observable<VehicleVariant> { return this.http.get<VehicleVariant>(`/api/vehicles/variants/${id}`); }
  dealersForVariant(variantId: string): Observable<Dealer[]> { return this.http.get<Dealer[]>(`/api/dealer-vehicles/${variantId}/dealers`); }
  dealers(): Observable<Dealer[]> { return this.http.get<Dealer[]>('/api/dealers'); }
  createEnquiry(request: CreateEnquiryRequest): Observable<CustomerEnquiry> { return this.http.post<CustomerEnquiry>('/api/enquiries', request); }
  myEnquiries(customerId: string): Observable<CustomerEnquiry[]> { return this.http.get<CustomerEnquiry[]>('/api/enquiries/my', { params: new HttpParams().set('customerId', customerId) }); }
  enquiry(id: string): Observable<CustomerEnquiry> { return this.http.get<CustomerEnquiry>(`/api/enquiries/${id}`); }
  leads(dealerId: string): Observable<Lead[]> { return this.http.get<Lead[]>('/api/leads', { params: new HttpParams().set('dealerId', dealerId) }); }
  lead(id: string): Observable<Lead> { return this.http.get<Lead>(`/api/leads/${id}`); }
  updateLeadStatus(id: string, request: UpdateLeadStatusRequest): Observable<Lead> { return this.http.patch<Lead>(`/api/leads/${id}/status`, request); }
  addLeadNote(id: string, request: CreateLeadNoteRequest): Observable<LeadNote> { return this.http.post<LeadNote>(`/api/leads/${id}/notes`, request); }
  leadHistory(id: string): Observable<StatusHistory[]> { return this.http.get<StatusHistory[]>(`/api/leads/${id}/history`); }
}
