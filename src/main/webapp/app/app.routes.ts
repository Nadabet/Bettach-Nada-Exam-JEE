import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ClientListComponent } from './client/client-list.component';
import { ClientAddComponent } from './client/client-add.component';
import { ClientEditComponent } from './client/client-edit.component';
import { CreditListComponent } from './credit/credit-list.component';
import { CreditAddComponent } from './credit/credit-add.component';
import { CreditEditComponent } from './credit/credit-edit.component';
import { RemboursementListComponent } from './remboursement/remboursement-list.component';
import { RemboursementAddComponent } from './remboursement/remboursement-add.component';
import { RemboursementEditComponent } from './remboursement/remboursement-edit.component';
import { ErrorComponent } from './error/error.component';


export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: $localize`:@@home.index.headline:Welcome to your new app!`
  },
  {
    path: 'clients',
    component: ClientListComponent,
    title: $localize`:@@client.list.headline:Clients`
  },
  {
    path: 'clients/add',
    component: ClientAddComponent,
    title: $localize`:@@client.add.headline:Add Client`
  },
  {
    path: 'clients/edit/:id',
    component: ClientEditComponent,
    title: $localize`:@@client.edit.headline:Edit Client`
  },
  {
    path: 'credits',
    component: CreditListComponent,
    title: $localize`:@@credit.list.headline:Credits`
  },
  {
    path: 'credits/add',
    component: CreditAddComponent,
    title: $localize`:@@credit.add.headline:Add Credit`
  },
  {
    path: 'credits/edit/:id',
    component: CreditEditComponent,
    title: $localize`:@@credit.edit.headline:Edit Credit`
  },
  {
    path: 'remboursements',
    component: RemboursementListComponent,
    title: $localize`:@@remboursement.list.headline:Remboursements`
  },
  {
    path: 'remboursements/add',
    component: RemboursementAddComponent,
    title: $localize`:@@remboursement.add.headline:Add Remboursement`
  },
  {
    path: 'remboursements/edit/:id',
    component: RemboursementEditComponent,
    title: $localize`:@@remboursement.edit.headline:Edit Remboursement`
  },
  {
    path: 'error',
    component: ErrorComponent,
    title: $localize`:@@error.page.headline:Error`
  },
  {
    path: '**',
    component: ErrorComponent,
    title: $localize`:@@notFound.headline:Page not found`
  }
];
