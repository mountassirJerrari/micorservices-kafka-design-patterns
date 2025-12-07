import { StatsCard } from "@/components/StatsCard";
import { Building2, TrendingUp, DollarSign, BarChart3 } from "lucide-react";
import { Card } from "@/components/ui/card";
import { useQuery } from "@tanstack/react-query";

// Mock data - à remplacer par les vraies données de l'API
const mockCompanies = [
  { id: '1', name: 'TechCorp', currentPrice: 1250, priceChange: 25, domain: 'IT' as const },
  { id: '2', name: 'AIVision', currentPrice: 3450, priceChange: -15, domain: 'AI' as const },
  { id: '3', name: 'BankPlus', currentPrice: 580, priceChange: 12, domain: 'Banque' as const },
];

const Dashboard = () => {
  // Statistiques calculées
  const totalCompanies = mockCompanies.length;
  const avgPrice = mockCompanies.reduce((sum, c) => sum + c.currentPrice, 0) / totalCompanies;
  const positiveMovers = mockCompanies.filter(c => c.priceChange > 0).length;
  const marketTrend = mockCompanies.reduce((sum, c) => sum + c.priceChange, 0) / totalCompanies;

  return (
    <div className="space-y-8">
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-2">Tableau de bord</h1>
        <p className="text-muted-foreground">Vue d'ensemble du marché boursier</p>
      </div>

      <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-4">
        <StatsCard
          title="Entreprises cotées"
          value={totalCompanies.toString()}
          icon={Building2}
          trend="neutral"
        />
        <StatsCard
          title="Prix moyen"
          value={`${avgPrice.toFixed(2)} MAD`}
          change={2.5}
          icon={DollarSign}
          trend="up"
        />
        <StatsCard
          title="Hausse du jour"
          value={positiveMovers.toString()}
          icon={TrendingUp}
          trend="up"
        />
        <StatsCard
          title="Tendance marché"
          value={`${marketTrend > 0 ? '+' : ''}${marketTrend.toFixed(2)}%`}
          change={Math.abs(marketTrend)}
          icon={BarChart3}
          trend={marketTrend >= 0 ? 'up' : 'down'}
        />
      </div>

      <div className="grid gap-6 lg:grid-cols-2">
        <Card className="p-6">
          <h3 className="text-lg font-semibold mb-4 text-foreground">Top gainants</h3>
          <div className="space-y-3">
            {mockCompanies
              .filter(c => c.priceChange > 0)
              .sort((a, b) => b.priceChange - a.priceChange)
              .map((company) => (
                <div key={company.id} className="flex items-center justify-between p-3 rounded-lg bg-success/5 border border-success/20">
                  <div>
                    <p className="font-medium text-foreground">{company.name}</p>
                    <p className="text-sm text-muted-foreground">{company.domain}</p>
                  </div>
                  <div className="text-right">
                    <p className="font-semibold text-foreground">{company.currentPrice} MAD</p>
                    <p className="text-sm text-success">+{company.priceChange.toFixed(2)}%</p>
                  </div>
                </div>
              ))}
          </div>
        </Card>

        <Card className="p-6">
          <h3 className="text-lg font-semibold mb-4 text-foreground">Top perdants</h3>
          <div className="space-y-3">
            {mockCompanies
              .filter(c => c.priceChange < 0)
              .sort((a, b) => a.priceChange - b.priceChange)
              .map((company) => (
                <div key={company.id} className="flex items-center justify-between p-3 rounded-lg bg-destructive/5 border border-destructive/20">
                  <div>
                    <p className="font-medium text-foreground">{company.name}</p>
                    <p className="text-sm text-muted-foreground">{company.domain}</p>
                  </div>
                  <div className="text-right">
                    <p className="font-semibold text-foreground">{company.currentPrice} MAD</p>
                    <p className="text-sm text-destructive">{company.priceChange.toFixed(2)}%</p>
                  </div>
                </div>
              ))}
          </div>
        </Card>
      </div>
    </div>
  );
};

export default Dashboard;
