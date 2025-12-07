import { Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Company } from "@/types/company";
import { TrendingUp, TrendingDown, Edit, Trash2 } from "lucide-react";
import { cn } from "@/lib/utils";

interface CompanyCardProps {
  company: Company;
  onEdit: (company: Company) => void;
  onDelete: (id: string) => void;
}

export const CompanyCard = ({ company, onEdit, onDelete }: CompanyCardProps) => {
  const isPositive = (company.priceChange || 0) >= 0;
  
  const domainColors: Record<string, string> = {
    'IT': 'bg-blue-500/10 text-blue-700 dark:text-blue-400 border-blue-500/20',
    'AI': 'bg-purple-500/10 text-purple-700 dark:text-purple-400 border-purple-500/20',
    'Banque': 'bg-green-500/10 text-green-700 dark:text-green-400 border-green-500/20',
    'Assurance': 'bg-orange-500/10 text-orange-700 dark:text-orange-400 border-orange-500/20',
    'Autre': 'bg-gray-500/10 text-gray-700 dark:text-gray-400 border-gray-500/20'
  };

  return (
    <Card className="p-6 hover:shadow-lg transition-all duration-300 border-border/50 group">
      <div className="space-y-4">
        <div className="flex items-start justify-between">
          <div className="space-y-1 flex-1">
            <h3 className="font-semibold text-lg text-foreground">{company.name}</h3>
            <Badge variant="outline" className={cn("text-xs", domainColors[company.domain])}>
              {company.domain}
            </Badge>
          </div>
          <div className="flex gap-2 opacity-0 group-hover:opacity-100 transition-opacity">
            <Button 
              size="icon" 
              variant="ghost"
              onClick={() => onEdit(company)}
              className="h-8 w-8"
            >
              <Edit className="h-4 w-4" />
            </Button>
            <Button 
              size="icon" 
              variant="ghost"
              onClick={() => onDelete(company.id)}
              className="h-8 w-8 text-destructive hover:text-destructive"
            >
              <Trash2 className="h-4 w-4" />
            </Button>
          </div>
        </div>

        <div className="space-y-2">
          <div className="flex items-baseline gap-2">
            <span className="text-3xl font-bold text-foreground">
              {company.currentPrice.toFixed(2)} MAD
            </span>
            {company.priceChange !== undefined && (
              <span className={cn(
                "text-sm font-medium flex items-center gap-1",
                isPositive ? "text-success" : "text-destructive"
              )}>
                {isPositive ? <TrendingUp className="h-4 w-4" /> : <TrendingDown className="h-4 w-4" />}
                {Math.abs(company.priceChange).toFixed(2)} ({Math.abs(company.priceChangePercent || 0).toFixed(2)}%)
              </span>
            )}
          </div>
          <p className="text-sm text-muted-foreground">
            Introduction: {new Date(company.listingDate).toLocaleDateString('fr-FR')}
          </p>
        </div>
      </div>
    </Card>
  );
};
