import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ApiService } from '../api.service';
import { Lead, LeadStatus } from '../models';

@Component({standalone:true,imports:[CommonModule,FormsModule,RouterLink],template:`
<section class="page-heading"><span class="eyebrow">DEALER</span><h1>Lead Dashboard</h1><p>Manage sales opportunities assigned to the selected dealer.</p></section>
<div class="toolbar card"><label>Dealer ID <input [(ngModel)]="dealerId"></label><button class="button primary" (click)="load()">Refresh</button></div>
<div class="stats"><div class="stat" *ngFor="let s of statuses"><strong>{{count(s)}}</strong><span>{{label(s)}}</span></div></div>
<div *ngIf="error" class="error card">{{error}}</div><div *ngIf="loading" class="card muted">Loading leads…</div>
<div class="card table-wrap" *ngIf="!loading && leads.length"><table><thead><tr><th>Lead</th><th>Customer</th><th>Variant</th><th>Status</th><th>Created</th><th>Updated</th><th></th></tr></thead><tbody><tr *ngFor="let l of leads"><td><code>{{idOf(l)|slice:0:8}}</code></td><td><code>{{l.customerId|slice:0:8}}</code></td><td><code>{{l.variantId|slice:0:8}}</code></td><td><span class="status" [class]="l.status.toLowerCase()">{{l.status}}</span></td><td>{{l.createdAt|date:'short'}}</td><td>{{l.updatedAt|date:'short'}}</td><td><a [routerLink]="['/dealer/leads',idOf(l)]">Manage</a></td></tr></tbody></table></div>
<div *ngIf="!loading&&!leads.length&&!error" class="card empty">No leads found for this dealer.</div>
`})
export class DealerDashboardComponent { private readonly api=inject(ApiService); dealerId='52222222-2222-2222-2222-222222222222'; leads:Lead[]=[]; loading=false;error='';statuses:LeadStatus[]=['NEW','CONTACTED','INTERESTED','FOLLOW_UP','CONVERTED','CLOSED'];constructor(){this.load()}load(){this.loading=true;this.error='';this.api.leads(this.dealerId.trim()).subscribe({next:x=>{this.leads=x;this.loading=false},error:e=>{this.error=e?.error?.message||'Unable to load dealer leads.';this.loading=false}})}count(s:LeadStatus){return this.leads.filter(x=>x.status===s).length}label(s:LeadStatus){return s.replace('_',' ')}idOf(l:Lead){return l.leadId||l.id||''}}
