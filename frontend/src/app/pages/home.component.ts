import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  standalone: true,
  imports: [RouterLink],
  template: `
    <section class="hero">
      <span class="eyebrow">PHASE 3 MVP</span>
      <h1>Customer enquiry to dealer lead, end to end.</h1>
      <p>Use the real Vehicle, Dealer and Enquiry services to create and track opportunities.</p>
      <div class="actions">
        <a class="button primary" routerLink="/customer/enquiries/new">Create Enquiry</a>
        <a class="button secondary" routerLink="/dealer/leads">Open Dealer Dashboard</a>
      </div>
    </section>
    <section class="grid three">
      <article class="card"><h3>Customer</h3><p>Select a vehicle, variant and dealer, then submit an enquiry.</p><a routerLink="/customer/enquiries">Track enquiries →</a></article>
      <article class="card"><h3>Dealer</h3><p>Review assigned leads, progress status, add internal notes and inspect history.</p><a routerLink="/dealer/leads">Manage leads →</a></article>
      <article class="card"><h3>Integrated</h3><p>Angular communicates with the existing Spring Boot REST services through the local development proxy.</p></article>
    </section>
  `
})
export class HomeComponent {}
