import { Building2, TrendingUp } from "lucide-react";
import { NavLink } from "@/components/NavLink";
import { cn } from "@/lib/utils";

export const Navbar = () => {
  return (
    <nav className="border-b border-border bg-card/50 backdrop-blur-sm sticky top-0 z-50">
      <div className="container mx-auto px-4">
        <div className="flex items-center justify-between h-16">
          <div className="flex items-center gap-2">
            <div className="p-2 bg-primary rounded-lg">
              <TrendingUp className="h-6 w-6 text-primary-foreground" />
            </div>
            <div>
              <h1 className="text-xl font-bold text-foreground">Stock Market</h1>
              <p className="text-xs text-muted-foreground">Système de gestion boursière</p>
            </div>
          </div>

          <div className="flex items-center gap-1">
            <NavLink
              to="/"
              end
              className="px-4 py-2 rounded-lg font-medium transition-all duration-200 text-muted-foreground hover:text-foreground hover:bg-secondary"
              activeClassName="bg-primary text-primary-foreground hover:bg-primary"
            >
              Dashboard
            </NavLink>
            <NavLink
              to="/companies"
              className="px-4 py-2 rounded-lg font-medium transition-all duration-200 text-muted-foreground hover:text-foreground hover:bg-secondary"
              activeClassName="bg-primary text-primary-foreground hover:bg-primary"
            >
              Entreprises
            </NavLink>
            <NavLink
              to="/quotations"
              className="px-4 py-2 rounded-lg font-medium transition-all duration-200 text-muted-foreground hover:text-foreground hover:bg-secondary"
              activeClassName="bg-primary text-primary-foreground hover:bg-primary"
            >
              Cotations
            </NavLink>
          </div>
        </div>
      </div>
    </nav>
  );
};
