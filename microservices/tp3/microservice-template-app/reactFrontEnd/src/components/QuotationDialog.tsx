import { useState } from "react";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { StockQuotation } from "@/types/company";
import { Company } from "@/types/company";

interface QuotationDialogProps {
  open: boolean;
  onClose: () => void;
  onSave: (quotation: Partial<StockQuotation>) => void;
  companies: Company[];
}

export const QuotationDialog = ({ open, onClose, onSave, companies }: QuotationDialogProps) => {
  const [formData, setFormData] = useState<Partial<StockQuotation>>({
    date: new Date().toISOString().split('T')[0],
    openValue: 0,
    highValue: 0,
    lowValue: 0,
    closeValue: 0,
    volume: 0,
    companyId: companies[0]?.id || ''
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSave(formData);
    onClose();
  };

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="sm:max-w-[600px] max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle>Nouvelle cotation</DialogTitle>
        </DialogHeader>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="space-y-2">
            <Label htmlFor="companyId">Entreprise</Label>
            <Select 
              value={formData.companyId} 
              onValueChange={(value) => setFormData({ ...formData, companyId: value })}
            >
              <SelectTrigger>
                <SelectValue />
              </SelectTrigger>
              <SelectContent>
                {companies.map((company) => (
                  <SelectItem key={company.id} value={company.id}>
                    {company.name}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <div className="space-y-2">
            <Label htmlFor="date">Date</Label>
            <Input
              id="date"
              type="date"
              value={formData.date}
              onChange={(e) => setFormData({ ...formData, date: e.target.value })}
              required
            />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="openValue">Ouverture (MAD)</Label>
              <Input
                id="openValue"
                type="number"
                step="0.01"
                value={formData.openValue}
                onChange={(e) => setFormData({ ...formData, openValue: parseFloat(e.target.value) })}
                required
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="closeValue">Fermeture (MAD)</Label>
              <Input
                id="closeValue"
                type="number"
                step="0.01"
                value={formData.closeValue}
                onChange={(e) => setFormData({ ...formData, closeValue: parseFloat(e.target.value) })}
                required
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="highValue">Plus haut (MAD)</Label>
              <Input
                id="highValue"
                type="number"
                step="0.01"
                value={formData.highValue}
                onChange={(e) => setFormData({ ...formData, highValue: parseFloat(e.target.value) })}
                required
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="lowValue">Plus bas (MAD)</Label>
              <Input
                id="lowValue"
                type="number"
                step="0.01"
                value={formData.lowValue}
                onChange={(e) => setFormData({ ...formData, lowValue: parseFloat(e.target.value) })}
                required
              />
            </div>
          </div>

          <div className="space-y-2">
            <Label htmlFor="volume">Volume</Label>
            <Input
              id="volume"
              type="number"
              value={formData.volume}
              onChange={(e) => setFormData({ ...formData, volume: parseInt(e.target.value) })}
              required
            />
          </div>

          <DialogFooter>
            <Button type="button" variant="outline" onClick={onClose}>
              Annuler
            </Button>
            <Button type="submit">
              Créer
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
};
