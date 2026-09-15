import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ApiService } from '../api.service';
import { Lead, LeadStatus, StatusHistory, LeadNote, VehicleVariant } from '../models';

@Component({standalone:true,imports:[CommonModule,FormsModule,RouterLink],template:`
<section class="page-heading"><a routerLink="/dealer/leads">← Dealer Leads</a><span class="eyebrow">DEALER</span><h1>Lead Management</h1></section>
<div *ngIf="error" class="error card">{{error}}</div><div *ngIf="loading" class="card muted">Loading lead…</div>
<div *ngIf="lead" class="grid two"><article class="card"><div class="detail-head"><div><h2>Lead {{lead.id||lead.leadId}}</h2><span class="status" [class]="lead.status.toLowerCase()">{{lead.status}}</span></div></div><dl><dt>Enquiry</dt><dd><code>{{lead.enquiryId}}</code></dd><dt>Customer</dt><dd><code>{{lead.customerId}}</code></dd><dt>Variant</dt><dd><code>{{lead.variantId}}</code></dd><dt>Dealer</dt><dd><code>{{lead.dealerId}}</code></dd><dt>Created</dt><dd>{{lead.createdAt|date:'medium'}}</dd><dt>Updated</dt><dd>{{lead.updatedAt|date:'medium'}}</dd></dl></article>
<article class="card"><h2>Update Status</h2><div class="field"><label>Next status</label><select [(ngModel)]="newStatus"><option *ngFor="let s of allowed" [value]="s">{{s}}</option></select></div><div class="field"><label>Reason <span class="muted">optional</span></label><textarea [(ngModel)]="reason" rows="3" maxlength="2000"></textarea></div><button class="button primary" (click)="update()" [disabled]="saving">{{saving?'Saving…':'Update Status'}}</button><div class="success" *ngIf="message">{{message}}</div></article>
<article class="card"><h2>Internal Note</h2><textarea [(ngModel)]="note" rows="4" maxlength="2000" placeholder="Dealer-only note..."></textarea><button class="button secondary" (click)="addNote()" [disabled]="!note.trim()||savingNote">{{savingNote?'Adding…':'Add Note'}}</button><div class="note" *ngFor="let n of notes"><strong>{{n.createdAt|date:'medium'}}</strong><p>{{n.content}}</p></div></article>
<article class="card"><h2>Status History</h2><div class="history" *ngIf="history.length"><div *ngFor="let h of history"><span class="history-dot"></span><div><strong>{{h.previousStatus||'—'}} → {{h.newStatus}}</strong><p>{{h.changedAt|date:'medium'}}<span *ngIf="h.reason"> · {{h.reason}}</span></p></div></div></div><p class="muted" *ngIf="!history.length">No history recorded yet.</p></article></div>
`})
export class LeadDetailComponent {
 private readonly api=inject(ApiService);private readonly route=inject(ActivatedRoute);lead?:Lead;history:StatusHistory[]=[];notes:LeadNote[]=[];loading=true;error='';saving=false;savingNote=false;message='';note='';reason='';newStatus:LeadStatus='CONTACTED';allowed:LeadStatus[]=[];private id='';
 constructor(){this.id=this.route.snapshot.paramMap.get('id')||'';this.load()}
 load(){this.api.lead(this.id).subscribe({next:x=>{this.lead=x;this.newStatus=this.next(x.status);this.allowed=this.nextOptions(x.status);this.loading=false;this.loadHistory()},error:e=>{this.error=e?.error?.message||'Lead not found.';this.loading=false}})}
 next(s:LeadStatus):LeadStatus{return ({NEW:'CONTACTED',CONTACTED:'INTERESTED',INTERESTED:'FOLLOW_UP',FOLLOW_UP:'CONVERTED',CONVERTED:'CLOSED',CLOSED:'CLOSED'} as Record<LeadStatus,LeadStatus>)[s]}
 nextOptions(s:LeadStatus):LeadStatus[]{const n=this.next(s);return n===s?[]:[n,'CLOSED'].filter((x,i,a)=>a.indexOf(x)===i) as LeadStatus[]}
 loadHistory(){this.api.leadHistory(this.id).subscribe({next:x=>this.history=x,error:()=>{}})}
 update(){if(!this.lead)return;this.saving=true;this.message='';this.api.updateLeadStatus(this.id,{status:this.newStatus,reason:this.reason.trim()||undefined}).subscribe({next:x=>{this.lead=x;this.saving=false;this.message=`Status updated to ${x.status}.`;this.reason='';this.allowed=this.nextOptions(x.status);this.newStatus=this.next(x.status);this.loadHistory()},error:e=>{this.saving=false;this.error=e?.error?.message||'Status update failed.'}})}
 addNote(){this.savingNote=true;this.api.addLeadNote(this.id,{content:this.note.trim()}).subscribe({next:n=>{this.notes=[n,...this.notes];this.note='';this.savingNote=false},error:e=>{this.error=e?.error?.message||'Unable to add note.';this.savingNote=false}})}
}
