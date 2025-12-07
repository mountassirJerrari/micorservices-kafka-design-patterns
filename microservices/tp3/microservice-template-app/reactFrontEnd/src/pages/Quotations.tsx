import { useState } from "react";
import { QuotationDialog } from "@/components/QuotationDialog";
import { Button } from "@/components/ui/button";
import { Plus } from "lucide-react";
import { StockQuotation, Company } from "@/types/company";
import { toast } from "sonner";
import { Card } from "@/components/ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Badge } from "@/components/ui/badge";

// Mock data
const mockCompanies: Company[] = [
  { id: '1', name: 'TechCorp', listingDate: '2023-01-15', currentPrice: 1250, domain: 'IT' },
  { id: '2', name: 'AIVision', listingDate: '2023-03-20', currentPrice: 3450, domain: 'AI' },
];

const mockQuotations: StockQuotation[] = [
  {
    id: '1',
    date: '2024-01-15',
    openValue: 1220,
    highValue: 1280,
    lowValue: 1210,
    closeValue: 1250,
    volume: 15000,
    companyId: '1'
  },
  {
    id: '2',
    date: '2024-01-15',
    openValue: 3400,
    highValue: 3480,
    lowValue: 3380,
    closeValue: 3450,
    volume: 8500,
    companyId: '2'
  },
];

const Quotations = () => {
  const [quotations, setQuotations] = useState<StockQuotation[]>(mockQuotations);
  const [dialogOpen, setDialogOpen] = useState(false);

  const handleSave = (quotationData: Partial<StockQuotation>) => {
    const newQuotation: StockQuotation = {
      ...quotationData,
      id: Date.now().toString(),
    } as StockQuotation;
    setQuotations([newQuotation, ...quotations]);
    toast.success('Cotation ajoutée avec succès');
  };

  const getCompanyName = (companyId: string) => {
    return mockCompanies.find(c => c.id === companyId)?.name || 'N/A';
  };

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold text-foreground mb-2">Cotations boursières</h1>
          <p className="text-muted-foreground">Gérer les cotations des entreprises</p>
        </div>
        <Button onClick={() => setDialogOpen(true)}>
          <Plus className="h-4 w-4 mr-2" />
          Nouvelle cotation
        </Button>
      </div>

      <Card>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>Date</TableHead>
              <TableHead>Entreprise</TableHead>
              <TableHead className="text-right">Ouverture</TableHead>
              <TableHead className="text-right">Plus haut</TableHead>
              <TableHead className="text-right">Plus bas</TableHead>
              <TableHead className="text-right">Fermeture</TableHead>
              <TableHead className="text-right">Volume</TableHead>
              <TableHead className="text-right">Variation</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {quotations.map((quotation) => {
              const change = quotation.closeValue - quotation.openValue;
              const changePercent = (change / quotation.openValue) * 100;
              const isPositive = change >= 0;

              return (
                <TableRow key={quotation.id}>
                  <TableCell className="font-medium">
                    {new Date(quotation.date).toLocaleDateString('fr-FR')}
                  </TableCell>
                  <TableCell>{getCompanyName(quotation.companyId)}</TableCell>
                  <TableCell className="text-right">{quotation.openValue.toFixed(2)}</TableCell>
                  <TableCell className="text-right text-success">{quotation.highValue.toFixed(2)}</TableCell>
                  <TableCell className="text-right text-destructive">{quotation.lowValue.toFixed(2)}</TableCell>
                  <TableCell className="text-right font-semibold">{quotation.closeValue.toFixed(2)}</TableCell>
                  <TableCell className="text-right">{quotation.volume.toLocaleString()}</TableCell>
                  <TableCell className="text-right">
                    <Badge variant={isPositive ? "default" : "destructive"} className={isPositive ? "bg-success" : ""}>
                      {isPositive ? '+' : ''}{change.toFixed(2)} ({changePercent.toFixed(2)}%)
                    </Badge>
                  </TableCell>
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </Card>

      {quotations.length === 0 && (
        <div className="text-center py-12">
          <p className="text-muted-foreground">Aucune cotation disponible</p>
        </div>
      )}

      <QuotationDialog
        open={dialogOpen}
        onClose={() => setDialogOpen(false)}
        onSave={handleSave}
        companies={mockCompanies}
      />
    </div>
  );
};

export default Quotations;
