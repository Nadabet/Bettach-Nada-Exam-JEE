import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputRowComponent } from 'app/common/input-row/input-row.component';
import { RemboursementService } from 'app/remboursement/remboursement.service';
import { RemboursementDTO } from 'app/remboursement/remboursement.model';
import { ErrorHandler } from 'app/common/error-handler.injectable';
import { updateForm, validDouble } from 'app/common/utils';


@Component({
  selector: 'app-remboursement-edit',
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './remboursement-edit.component.html'
})
export class RemboursementEditComponent implements OnInit {

  remboursementService = inject(RemboursementService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  creditValues?: Map<number,string>;
  currentId?: number;

  editForm = new FormGroup({
    id: new FormControl({ value: null, disabled: true }),
    date: new FormControl(null, [Validators.required]),
    montant: new FormControl(null, [Validators.required, validDouble]),
    type: new FormControl(null, [Validators.required, Validators.maxLength(50)]),
    credit: new FormControl(null, [Validators.required])
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      updated: $localize`:@@remboursement.update.success:Remboursement was updated successfully.`
    };
    return messages[key];
  }

  ngOnInit() {
    this.currentId = +this.route.snapshot.params['id'];
    this.remboursementService.getCreditValues()
        .subscribe({
          next: (data) => this.creditValues = data,
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
    this.remboursementService.getRemboursement(this.currentId!)
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
    const data = new RemboursementDTO(this.editForm.value);
    this.remboursementService.updateRemboursement(this.currentId!, data)
        .subscribe({
          next: () => this.router.navigate(['/remboursements'], {
            state: {
              msgSuccess: this.getMessage('updated')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.editForm, this.getMessage)
        });
  }

}
