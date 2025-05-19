export class CreditDTO {

  constructor(data:Partial<CreditDTO>) {
    Object.assign(this, data);
  }

  id?: number|null;
  creditType?: string|null;
  dateDemande?: string|null;
  statut?: string|null;
  dateAcceptation?: string|null;
  montant?: number|null;
  dureeRemboursement?: number|null;
  tauxInteret?: number|null;
  motif?: string|null;
  typeBien?: string|null;
  raisonSociale?: string|null;
  client?: number|null;

}
