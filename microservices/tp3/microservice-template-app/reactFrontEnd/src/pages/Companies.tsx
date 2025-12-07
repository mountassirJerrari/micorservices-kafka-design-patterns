import { useState } from "react";
import { CompanyCard } from "@/components/CompanyCard";
import { CompanyDialog } from "@/components/CompanyDialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Plus, Search } from "lucide-react";
import { Company } from "@/types/company";
import { toast } from "sonner";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";

// Mock data - à remplacer par l'API
const mockCompanies: Company[] = [
  {
    id: '1',
    name: 'TechCorp Morocco',
    listingDate: '2023-01-15',
    currentPrice: 1250.50,
    domain: 'IT',
    priceChange: 25.30,
    priceChangePercent: 2.07
  },
  {
    id: '2',
    name: 'AIVision Maroc',
    listingDate: '2023-03-20',
    currentPrice: 3450.00,
    domain: 'AI',
    priceChange: -15.20,
    priceChangePercent: -0.44
  },
  {
    id: '3',
    name: 'BankPlus',
    listingDate: '2022-11-10',
    currentPrice: 580.75,
    domain: 'Banque',
    priceChange: 12.50,
    priceChangePercent: 2.20
  },
  {
    id: '4',
    name: 'AssuranceSecure',
    listingDate: '2023-05-01',
    currentPrice: 425.30,
    domain: 'Assurance',
    priceChange: -8.20,
    priceChangePercent: -1.89
  },
];

const Companies = () => {
  const [companies, setCompanies] = useState<Company[]>(mockCompanies);
  const [dialogOpen, setDialogOpen] = useState(false);
  const [selectedCompany, setSelectedCompany] = useState<Company | undefined>();
  const [searchTerm, setSearchTerm] = useState('');
  const [filterDomain, setFilterDomain] = useState<string>('all');

  const handleSave = (companyData: Partial<Company>) => {
    if (selectedCompany) {
      // Update
      setCompanies(companies.map(c => 
        c.id === selectedCompany.id ? { ...c, ...companyData } : c
      ));
      toast.success('Entreprise mise à jour avec succès');
    } else {
      // Create
      const newCompany: Company = {
        ...companyData,
        id: Date.now().toString(),
        priceChange: 0,
        priceChangePercent: 0
      } as Company;
      setCompanies([...companies, newCompany]);
      toast.success('Entreprise créée avec succès');
    }
    setSelectedCompany(undefined);
  };

  const handleEdit = (company: Company) => {
    setSelectedCompany(company);
    setDialogOpen(true);
  };

  const handleDelete = (id: string) => {
    setCompanies(companies.filter(c => c.id !== id));
    toast.success('Entreprise supprimée');
  };

  const filteredCompanies = companies.filter(company => {
    const matchesSearch = company.name.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesDomain = filterDomain === 'all' || company.domain === filterDomain;
    return matchesSearch && matchesDomain;
  });

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold text-foreground mb-2">Entreprises cotées</h1>
          <p className="text-muted-foreground">Gérer les entreprises en bourse</p>
        </div>
        <Button onClick={() => { setSelectedCompany(undefined); setDialogOpen(true); }}>
          <Plus className="h-4 w-4 mr-2" />
          Nouvelle entreprise
        </Button>
      </div>

      <div className="flex gap-4">
        <div className="relative flex-1">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
          <Input
            placeholder="Rechercher une entreprise..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="pl-10"
          />
        </div>
        <Select value={filterDomain} onValueChange={setFilterDomain}>
          <SelectTrigger className="w-[200px]">
            <SelectValue placeholder="Filtrer par domaine" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">Tous les domaines</SelectItem>
            <SelectItem value="IT">IT</SelectItem>
            <SelectItem value="AI">AI</SelectItem>
            <SelectItem value="Banque">Banque</SelectItem>
            <SelectItem value="Assurance">Assurance</SelectItem>
            <SelectItem value="Autre">Autre</SelectItem>
          </SelectContent>
        </Select>
      </div>

      <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
        {filteredCompanies.map((company) => (
          <CompanyCard
            key={company.id}
            company={company}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />
        ))}
      </div>

      {filteredCompanies.length === 0 && (
        <div className="text-center py-12">
          <p className="text-muted-foreground">Aucune entreprise trouvée</p>
        </div>
      )}

      <CompanyDialog
        open={dialogOpen}
        onClose={() => {
          setDialogOpen(false);
          setSelectedCompany(undefined);
        }}
        onSave={handleSave}
        company={selectedCompany}
      />
    </div>
  );
};

export default Companies;
