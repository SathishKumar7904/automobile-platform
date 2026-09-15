import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ApiService } from '../api.service';
import { CustomerEnquiry, LeadStatus } from '../models';

@Component({ standalone: true, imports: [CommonModule, FormsModule, RouterLink], template: `
<section class="page-heading"><span class="eyebrow">CUSTOMER</span><h1>My Enquiries</h1><p>Track the current status of your submitted enquiries.</p></section>
<div class="toolbar card"><label>Customer ID <input [(ngModel)]="customerId"></label><button class="button primary" (click)="load()">Refresh</button><a class="button secondary" routerLink="/customer/enquiries/new">New Enquiry</a></div>
<div *ngIf="error" class="error card">{{ error }}</div>
<div *ngIf="loading" class="card muted">Loading enquiries…</div>
<div class="card table-wrap" *ngIf="!loading && enquiries.length"><table><thead><tr><th>Enquiry</th><th>Vehicle / Variant</th><th>Dealer</th><th>Status</th><th>Submitted</th><th></th></tr></thead><tbody><tr *ngFor="let e of enquiries"><td><code>{{ e.enquiryId | slice:0:8 }}</code></td><td><code>{{ e.variantId | slice:0:8 }}</code></td><td><code>{{ e.dealerId | slice:0:8 }}</code></td><td><span class="status" [class]="e.status.toLowerCase()">{{ e.status }}</span></td><td>{{ e.submittedAt | date:'medium' }}</td><td><a [routerLink]="['/customer/enquiries', e.enquiryId]">View</a></td></tr></tbody></table></div>
<div *ngIf="!loading && !enquiries.length && !error" class="card empty">No enquiries found for this customer.</div>
` })
export class MyEnquiriesComponent {
  private readonly api=inject(ApiService); customerId='be019186-e22f-4cb9-9b74-104343f490f7'; enquiries:CustomerEnquiry[]=[]; loading=false; error='';
  constructor(){this.load();} load(){this.loading=true;this.error='';this.api.myEnquiries(this.customerId.trim()).subscribe({next:x=>{this.enquiries=x;this.loading=false},error:e=>{this.error=this.msg(e);this.loading=false}})}
  private msg(e:any){return e?.error?.message||'Unable to load enquiries.'}
}
