# Rapport  : Le Patron de Conception Adaptateur (Adapter)

## Introduction
Ce rapport présente une implémentation simple et illustrative du patron de conception **Adaptateur** (Adapter Design Pattern). Ce patron structurel permet à des interfaces incompatibles de collaborer. Il est souvent comparé à un adaptateur physique (comme un adaptateur de prise électrique) qui permet à deux objets de se connecter alors qu'ils n'ont pas été conçus pour cela.

## Problème
Dans notre scénario, nous disposons d'une **Unité Centrale** (Client) conçue pour envoyer des signaux vidéo via une interface **HDMI**. Cependant, nous possédons également un vieil **Écran VGA** (Adaptee) qui ne comprend pas le signal HDMI mais attend un signal VGA.
L'Unité Centrale ne peut pas se connecter directement à l'Écran VGA car leurs interfaces sont incompatibles.

## Solution
Nous avons implémenté un **Adaptateur HDMI vers VGA**.
- **Client** : `UniteCentrale` (utilise l'interface `Hdmi`).
- **Target (Cible)** : `Hdmi` (l'interface attendue par le client).
- **Adaptee (Adapté)** : `Vga` / `EcranVga` (l'interface existante incompatible).
- **Adapter (Adaptateur)** : `HdmiVgaAdapter`.

L'adaptateur implémente l'interface `Hdmi` (pour être connectable à l'unité centrale) et possède une référence vers un objet `Vga`. Lorsqu'il reçoit un appel via la méthode `view()` (HDMI), il convertit les données et délègue l'appel à la méthode `print()` de l'objet VGA.

## Structure du Code
1. **`Hdmi.java`** : Interface définissant la méthode `view(byte[] data)`.
2. **`Vga.java`** : Interface définissant la méthode `print(String message)`.
3. **`Tv.java`** : Implémentation standard de HDMI (fonctionne directement).
4. **`EcranVga.java`** : Implémentation de VGA (incompatible directement avec HDMI).
5. **`HdmiVgaAdapter.java`** : La classe adaptateur qui fait le pont. Elle convertit les octets HDMI en chaîne de caractères pour l'écran VGA.
6. **`UniteCentrale.java`** : La classe qui utilise l'interface HDMI pour afficher du contenu.

## Conclusion
Le patron Adaptateur est essentiel pour intégrer de nouveaux composants avec des systèmes existants sans modifier leur code source (respect du principe Open/Closed). Dans notre exemple, l'Unité Centrale peut désormais afficher du contenu sur un écran VGA sans savoir qu'elle ne communique pas avec un véritable écran HDMI.
