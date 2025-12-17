# Rapport sur les Patrons de Conception : Proxy et Template Method

**Auteur :** Mountassir Jerrari

## Introduction
Ce projet illustre l'implémentation de deux patrons de conception structurels et comportementaux fondamentaux : le patron **Proxy** et le patron **Template Method**. Ces exemples sont conçus pour démontrer l'utilité de ces modèles dans des scénarios de développement logiciel courants.

## 1. Patron Proxy (Proxy Pattern)
Le patron Proxy fournit un substitut ou un espace réservé pour un autre objet afin de contrôler l'accès à celui-ci.

### Scénario : Gestion de Transaction
Dans notre exemple, nous utilisons un proxy pour gérer les transactions autour d'un service de paiement.
- **Interface `PaymentService`** : Définit le contrat pour le traitement des paiements.
- **Classe `RealPaymentService`** : Implémente la logique métier réelle du paiement.
- **Classe `PaymentTransactionProxy`** : Agit comme un intermédiaire. Il ajoute des fonctionnalités de gestion de transaction (début, validation, annulation) avant et après l'appel à la méthode réelle, sans modifier le code de la classe de service réelle.

Ce modèle est essentiel pour séparer les préoccupations transversales (comme la gestion des transactions, la journalisation ou la sécurité) de la logique métier principale.

## 2. Patron Template Method (Template Method Pattern)
Le patron Template Method définit le squelette d'un algorithme dans une opération, en différant certaines étapes aux sous-classes.

### Scénario : Recette de Cuisine
L'exemple modélise un processus de cuisine généralisé.
- **Classe Abstraite `CookingRecipe`** : Définit la méthode modèle `cookRecipe()` qui orchestre les étapes de la préparation (préparer les ingrédients, cuisiner, servir). Elle implémente les étapes communes et déclare les étapes variables comme abstraites.
- **Classes Concrètes `PastaRecipe` et `PizzaRecipe`** : Fournissent les implémentations spécifiques pour les étapes `prepareIngredients()` et `cook()`.

Ce modèle favorise la réutilisation du code en centralisant la structure de l'algorithme tout en permettant aux sous-classes de redéfinir certaines étapes spécifiques sans changer la structure globale.

## Conclusion
L'utilisation de ces patrons de conception permet de créer des systèmes plus modulaires, maintenables et extensibles. Le Proxy assure un contrôle d'accès et une séparation des responsabilités, tandis que le Template Method permet de standardiser des processus tout en offrant de la flexibilité.
