import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputRowComponent } from 'app/common/input-row/input-row.component';
import { CreditService } from 'app/credit/credit.service';
import { CreditDTO } from 'app/credit/credit.model';
import { ErrorHandler } from 'app/common/error-handler.injectable';
import { updateForm, validDouble } from 'app/common/utils';


@Component({
  selector: 'app-credit-edit',
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './credit-edit.component.html'
})
export class CreditEditComponent implements OnInit {

  creditService = inject(CreditService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  clientValues?: Map<number,string>;
  currentId?: number;

  editForm = new FormGroup({
    id: new FormControl({ value: null, disabled: true }),
    creditType: new FormControl(null, [Validators.required, Validators.maxLength(50)]),
    dateDemande: new FormControl(null, [Validators.required]),
    statut: new FormControl(null, [Validators.required, Validators.maxLength(50)]),
    dateAcceptation: new FormControl(null),
    montant: new FormControl(null, [Validators.required, validDouble]),
    dureeRemboursement: new FormControl(null, [Validators.required]),
    tauxInteret: new FormControl(null, [Validators.required, validDouble]),
    motif: new FormControl(null, [Validators.maxLength(255)]),
    typeBien: new FormControl(null, [Validators.maxLength(50)]),
    raisonSociale: new FormControl(null, [Validators.maxLength(255)]),
    client: new FormControl(null, [Validators.required])
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      updated: $localize`:@@credit.update.success:Credit was updated successfully.`
    };
    return messages[key];
  }

  ngOnInit() {
    this.currentId = +this.route.snapshot.params['id'];
    this.creditService.getClientValues()
        .subscribe({
          next: (data) => this.clientValues = data,
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
    this.creditService.getCredit(this.currentId!)
        .subscribe({
          next: (data) => updateForm(this.editForm, data),
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

  handleSubmit() {
    window.scrollTo(0, 0);
    this.editForm.markAllAsTouched();
    if (!this.editForm.valid) {
      return;
    }
    const data = new CreditDTO(this.editForm.value);
    this.creditService.updateCredit(this.currentId!, data)
        .subscribe({
          next: () => this.router.navigate(['/credits'], {
            state: {
              msgSuccess: this.getMessage('updated')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.editForm, this.getMessage)
        });
  }

}
