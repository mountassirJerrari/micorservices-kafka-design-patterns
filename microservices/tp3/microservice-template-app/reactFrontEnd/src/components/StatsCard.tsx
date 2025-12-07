import { Card } from "@/components/ui/card";
import { LucideIcon } from "lucide-react";
import { cn } from "@/lib/utils";

interface StatsCardProps {
  title: string;
  value: string;
  change?: number;
  icon: LucideIcon;
  trend?: 'up' | 'down' | 'neutral';
}

export const StatsCard = ({ title, value, change, icon: Icon, trend = 'neutral' }: StatsCardProps) => {
  return (
    <Card className="p-6 hover:shadow-lg transition-all duration-300 border-border/50">
      <div className="flex items-start justify-between">
        <div className="space-y-2 flex-1">
          <p className="text-sm font-medium text-muted-foreground">{title}</p>
          <p className="text-3xl font-bold text-foreground">{value}</p>
          {change !== undefined && (
            <p className={cn(
              "text-sm font-medium flex items-center gap-1",
              trend === 'up' && "text-success",
              trend === 'down' && "text-destructive",
              trend === 'neutral' && "text-muted-foreground"
            )}>
              {trend === 'up' && '↑'}
              {trend === 'down' && '↓'}
              {Math.abs(change)}%
            </p>
          )}
        </div>
        <div className={cn(
          "p-3 rounded-lg",
          trend === 'up' && "bg-success/10",
          trend === 'down' && "bg-destructive/10",
          trend === 'neutral' && "bg-primary/10"
        )}>
          <Icon className={cn(
            "h-6 w-6",
            trend === 'up' && "text-success",
            trend === 'down' && "text-destructive",
            trend === 'neutral' && "text-primary"
          )} />
        </div>
      </div>
    </Card>
  );
};
