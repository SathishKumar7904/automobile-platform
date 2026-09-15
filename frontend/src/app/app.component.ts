import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  template: `
    <header class="topbar">
      <a class="brand" routerLink="/">Automobile Digital Platform</a>
      <nav>
        <a routerLink="/customer/enquiries/new" routerLinkActive="active">New Enquiry</a>
        <a routerLink="/customer/enquiries" routerLinkActive="active">My Enquiries</a>
        <a routerLink="/dealer/leads" routerLinkActive="active">Dealer Leads</a>
      </nav>
    </header>
    <main class="page-shell"><router-outlet /></main>
    <footer>Phase 3 MVP · Dealer Lead Management</footer>
  `
})
export class AppComponent {}
