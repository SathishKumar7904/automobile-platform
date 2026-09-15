import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home.component';
import { EnquiryCreateComponent } from './pages/enquiry-create.component';
import { MyEnquiriesComponent } from './pages/my-enquiries.component';
import { EnquiryDetailComponent } from './pages/enquiry-detail.component';
import { DealerDashboardComponent } from './pages/dealer-dashboard.component';
import { LeadDetailComponent } from './pages/lead-detail.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'customer/enquiries/new', component: EnquiryCreateComponent },
  { path: 'customer/enquiries', component: MyEnquiriesComponent },
  { path: 'customer/enquiries/:id', component: EnquiryDetailComponent },
  { path: 'dealer/leads', component: DealerDashboardComponent },
  { path: 'dealer/leads/:id', component: LeadDetailComponent },
  { path: '**', redirectTo: '' }
];
