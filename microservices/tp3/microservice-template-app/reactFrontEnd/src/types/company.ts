export interface Company {
  id: string;
  name: string;
  listingDate: string;
  currentPrice: number;
  domain: 'IT' | 'AI' | 'Banque' | 'Assurance' | 'Autre';
  priceChange?: number;
  priceChangePercent?: number;
}

export interface StockQuotation {
  id: string;
  date: string;
  openValue: number;
  highValue: number;
  lowValue: number;
  closeValue: number;
  volume: number;
  companyId: string;
}
