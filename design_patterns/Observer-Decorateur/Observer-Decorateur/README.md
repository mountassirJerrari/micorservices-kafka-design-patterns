# Rapport de Mise en Œuvre des Patrons de Conception

**Auteur :** Mountassir Jerrar


## 1. Introduction
Ce projet démontre la mise en œuvre pratique de deux patrons de conception logicielle fondamentaux : le **Patron Observateur (Observer Pattern)** et le **Patron Décorateur (Decorator Pattern)**. L'objectif est d'illustrer comment ces patrons résolvent des problèmes de conception spécifiques en utilisant Java.

## 2. Patron Observateur (Observer Pattern)
Le patron Observateur est un patron de conception comportemental qui définit une dépendance de type "un-à-plusieurs" entre des objets, de sorte que lorsqu'un objet change d'état, tous ses dépendants en sont informés et mis à jour automatiquement.

### 2.1 Scénario : Système de Notification YouTube
Nous avons implémenté une simulation d'un système de notification de chaîne YouTube.
*   **Sujet (Channel) :** L'objet qui détient l'état (vidéos mises en ligne) et notifie les observateurs.
*   **Observateur (Subscriber) :** Les objets qui souhaitent être notifiés lorsqu'une nouvelle vidéo est mise en ligne.

### 2.2 Détails de l'Implémentation
*   **Package :** `org.example.observer`
*   **Interfaces Clés :**
    *   `Subject` : Définit les méthodes pour s'abonner (`subscribe`), se désabonner (`unsubscribe`) et notifier les observateurs (`notifyObservers`).
    *   `Observer` : Définit la méthode `update` appelée par le Sujet.
*   **Classes Concrètes :**
    *   `Channel` : Implémente `Subject`. Maintient une liste d'abonnés et les notifie lorsque `uploadVideo()` est appelé.
    *   `Subscriber` : Implémente `Observer`. Reçoit les notifications et les affiche dans la console.
    *   `Youtube` : Une classe utilitaire pour gérer plusieurs chaînes.

## 3. Patron Décorateur (Decorator Pattern)
Le patron Décorateur est un patron de conception structurel qui permet d'ajouter dynamiquement des comportements à un objet individuel, sans affecter le comportement des autres objets de la même classe.

### 3.1 Scénario : Calcul du Coût dans un Café
Nous avons implémenté un exemple classique impliquant des boissons et des ingrédients supplémentaires.
*   **Composant (Beverage) :** La classe abstraite de base pour toutes les boissons.
*   **Composant Concret (Espresso) :** Un type spécifique de boisson.
*   **Décorateur (BeverageDecorator) :** Classe abstraite qui étend `Beverage` et enveloppe un objet `Beverage`.
*   **Décorateurs Concrets (Milk, Chocolate) :** Ajoutent des fonctionnalités (coût et description) à la boisson enveloppée.

### 3.2 Détails de l'Implémentation
*   **Package :** `org.example.decorator`
*   **Classes Clés :**
    *   `Beverage` : Classe de base abstraite avec `description` et `cost()`.
    *   `Espresso` : Implémentation concrète d'une boisson de base.
    *   `BeverageDecorator` : Décorateur abstrait étendant `Beverage`.
    *   `Milk`, `Chocolate` : Décorateurs concrets qui ajoutent leur propre coût au coût de la boisson enveloppée.

## 4. Conclusion
Ce projet démontre avec succès la flexibilité et la réutilisabilité offertes par les patrons de conception. Le patron Observateur découple la chaîne de ses abonnés, permettant une gestion dynamique des abonnements. Le patron Décorateur permet des combinaisons flexibles d'ingrédients de boissons sans créer une explosion de sous-classes.

