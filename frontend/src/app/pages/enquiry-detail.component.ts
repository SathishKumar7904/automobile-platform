import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ApiService } from '../api.service';
import { CustomerEnquiry, LeadStatus } from '../models';

@Component({standalone:true,imports:[CommonModule,RouterLink],template:`
<section class="page-heading"><a routerLink="/customer/enquiries">← My Enquiries</a><h1>Enquiry Details</h1></section>
<div *ngIf="error" class="error card">{{error}}</div>
<div *ngIf="loading" class="card muted">Loading…</div>
<div *ngIf="enquiry" class="grid two"><article class="card"><h2>Enquiry</h2><dl><dt>Enquiry ID</dt><dd><code>{{enquiry.enquiryId}}</code></dd><dt>Lead ID</dt><dd><code>{{enquiry.leadId}}</code></dd><dt>Vehicle Variant</dt><dd><code>{{enquiry.variantId}}</code></dd><dt>Dealer</dt><dd><code>{{enquiry.dealerId}}</code></dd><dt>Submitted</dt><dd>{{enquiry.submittedAt | date:'medium'}}</dd><dt>Message</dt><dd>{{enquiry.message || 'No message provided.'}}</dd></dl></article><article class="card"><h2>Status</h2><div class="current-status"><span class="status" [class]="enquiry.status.toLowerCase()">{{enquiry.status}}</span><p>{{statusText(enquiry.status)}}</p></div><h3>Customer timeline</h3><div class="timeline"><div class="timeline-item" *ngFor="let s of statuses;let i=index" [class.done]="rank(enquiry.status)>=rank(s)"><span></span><div><strong>{{s}}</strong><p>{{rank(enquiry.status)>=rank(s)?'Reached':'Pending'}}</p></div></div></div></article></div>
`})
export class EnquiryDetailComponent {
 private readonly api=inject(ApiService); private readonly route=inject(ActivatedRoute); enquiry?:CustomerEnquiry; loading=true; error=''; statuses:LeadStatus[]=['NEW','CONTACTED','INTERESTED','FOLLOW_UP','CONVERTED','CLOSED'];
 constructor(){const id=this.route.snapshot.paramMap.get('id');if(id)this.api.enquiry(id).subscribe({next:x=>{this.enquiry=x;this.loading=false},error:e=>{this.error=e?.error?.message||'Enquiry not found.';this.loading=false}})}
 rank(s:LeadStatus){return this.statuses.indexOf(s)} statusText(s:LeadStatus){return s==='NEW'?'Your enquiry has been received.':s==='CONTACTED'?'The dealer has contacted you.':s==='INTERESTED'?'The dealer marked the opportunity as interested.':s==='FOLLOW_UP'?'A dealer follow-up is in progress.':s==='CONVERTED'?'The lead was converted.':'The lead is closed.'}
}
