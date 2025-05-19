export class RemboursementDTO {

  constructor(data:Partial<RemboursementDTO>) {
    Object.assign(this, data);
  }

  id?: number|null;
  date?: string|null;
  montant?: number|null;
  type?: string|null;
  credit?: number|null;

}
