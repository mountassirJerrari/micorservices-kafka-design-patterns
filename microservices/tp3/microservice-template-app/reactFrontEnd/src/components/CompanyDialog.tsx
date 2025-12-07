import { useState } from "react";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Company } from "@/types/company";

interface CompanyDialogProps {
  open: boolean;
  onClose: () => void;
  onSave: (company: Partial<Company>) => void;
  company?: Company;
}

export const CompanyDialog = ({ open, onClose, onSave, company }: CompanyDialogProps) => {
  const [formData, setFormData] = useState<Partial<Company>>(
    company || {
      name: '',
      listingDate: new Date().toISOString().split('T')[0],
      currentPrice: 0,
      domain: 'IT'
    }
  );

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSave(formData);
    onClose();
  };

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="sm:max-w-[500px]">
        <DialogHeader>
          <DialogTitle>{company ? 'Modifier l\'entreprise' : 'Nouvelle entreprise'}</DialogTitle>
        </DialogHeader>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="space-y-2">
            <Label htmlFor="name">Nom de l'entreprise</Label>
            <Input
              id="name"
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              required
            />
          </div>

          <div className="space-y-2">
            <Label htmlFor="domain">Domaine</Label>
            <Select 
              value={formData.domain} 
              onValueChange={(value) => setFormData({ ...formData, domain: value as Company['domain'] })}
            >
              <SelectTrigger>
                <SelectValue />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="IT">IT</SelectItem>
                <SelectItem value="AI">AI</SelectItem>
                <SelectItem value="Banque">Banque</SelectItem>
                <SelectItem value="Assurance">Assurance</SelectItem>
                <SelectItem value="Autre">Autre</SelectItem>
              </SelectContent>
            </Select>
          </div>

          <div className="space-y-2">
            <Label htmlFor="listingDate">Date d'introduction</Label>
            <Input
              id="listingDate"
              type="date"
              value={formData.listingDate}
              onChange={(e) => setFormData({ ...formData, listingDate: e.target.value })}
              required
            />
          </div>

          <div className="space-y-2">
            <Label htmlFor="currentPrice">Prix actuel (MAD)</Label>
            <Input
              id="currentPrice"
              type="number"
              step="0.01"
              value={formData.currentPrice}
              onChange={(e) => setFormData({ ...formData, currentPrice: parseFloat(e.target.value) })}
              required
            />
          </div>

          <DialogFooter>
            <Button type="button" variant="outline" onClick={onClose}>
              Annuler
            </Button>
            <Button type="submit">
              {company ? 'Mettre à jour' : 'Créer'}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
};
