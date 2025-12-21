# Rapport de Travaux Pratiques : Programmation Orientée Aspect (AOP)

## 1. Fondements Théoriques : AspectJ vs Spring AOP

La Programmation Orientée Aspect (AOP) permet de modulariser les préoccupations transversales. Deux approches majeures coexistent dans l'écosystème Java : **AspectJ** et **Spring AOP**.

### AspectJ : L'AOP Statique (et Load-Time)
AspectJ est une extension du langage Java qui offre une implémentation complète de l'AOP. 
- **Tissage (Weaving)** : Il utilise principalement le tissage statique (au moment de la compilation ou post-compilation) ou le tissage au chargement des classes (Load-Time Weaving).
- **Performance** : Comme le code de l'aspect est injecté directement dans le bytecode, il n'y a pas de surcoût lié à l'exécution de proxies.
- **Flexibilité** : AspectJ peut intercepter n'importe quel point de jonction (accès aux attributs, appels de constructeurs, méthodes privées, etc.).

### Spring AOP : L'AOP Dynamique
Spring AOP est basé sur des **Proxies Dynamiques**.
- **Tissage** : Le tissage se fait au moment de l'exécution (Runtime). Spring crée un objet proxy qui enveloppe l'objet cible.
- **Limitations** : Il ne peut intercepter que les appels de méthodes publiques sur des beans gérés par le conteneur Spring. Il ne peut pas intercepter les appels internes (auto-invocation).
- **Simplicité** : Il ne nécessite pas de compilateur spécial (ajc) et s'intègre naturellement dans l'écosystème Spring.

| Caractéristique | AspectJ | Spring AOP |
| :--- | :--- | :--- |
| **Type de tissage** | Statique (Compile/Post-compile/Load-time) | Dynamique (Runtime) |
| **Performance** | Excellente (Bytecode direct) | Légèrement inférieure (Proxies) |
| **Points de jonction** | Tous (attributs, constructeurs, méthodes) | Uniquement méthodes publiques |
| **Complexité** | Plus élevée (nécessite `ajc`) | Simple (Proxies Java standard) |

---

## 2. Analyse du Module `aspectj_user_case` : Étude de Cas Bancaire

Ce module illustre l'application pratique de l'AOP dans un contexte métier critique.

### A. Sécurité et Authentification (`SecurityAspect`)
L'aspect de sécurité intercepte le point d'entrée de l'application (`Application.start`).
- **Mécanisme** : Utilisation de l'avis `@Around`. L'aspect prend le contrôle total du flux.
- **Logique** : Il demande des identifiants à l'utilisateur. Si l'authentification échoue (`root`/`1234`), il lève une `RuntimeException`, empêchant ainsi l'exécution de la méthode cible. Cela garantit que la logique métier ne démarre jamais sans autorisation.

### B. Journalisation et Monitoring (`LoggingAspect`)
Cet aspect automatise la traçabilité des opérations bancaires.
- **Pointcut** : Cible toutes les méthodes du package `org.example.metier`.
- **Fonctionnalité** : Il enregistre l'état avant et après chaque appel de méthode dans un fichier `log.xml`.
- **Mesure de Performance** : En calculant la différence de temps entre le début et la fin de l'exécution (`System.currentTimeMillis()`), il fournit des métriques précises sur la durée de chaque opération (verser, retirer, consulter).

### C. Correction de Bug Non-Intrusive (`PatchRetraitAspect`)
C'est l'exemple le plus puissant de la séparation des préoccupations.
- **Problématique** : La méthode `retirer` dans `MetierBanqueImpl` possède un bug volontaire : elle ne vérifie pas si le solde est suffisant.
- **Solution AOP** : L'aspect intercepte l'appel à `retirer`, récupère le solde actuel via l'objet cible (`joinPoint.getTarget()`), et vérifie si le montant demandé est disponible.
- **Impact** : Le bug est corrigé sans toucher à une seule ligne du code source de `MetierBanqueImpl`. Cela illustre comment l'AOP peut être utilisé pour appliquer des "patches" de sécurité ou de logique sur du code legacy.

---

## 3. Analyse du Module `aop-project` : Exploration des Advices

Ce module se concentre sur la syntaxe et le cycle de vie des aspects AspectJ via la classe `SecondAspect`.

### Hiérarchie des Avis (Advices)
L'aspect définit un point de coupure sur la méthode `main` et applique quatre types d'avis pour démontrer leur ordre d'exécution :
1.  **`@Before`** : S'exécute avant le corps de la méthode. Idéal pour l'initialisation.
2.  **`@After`** : S'exécute après la méthode, qu'elle se termine normalement ou par une exception (équivalent au bloc `finally`).
3.  **`@AfterReturning`** : S'exécute uniquement si la méthode se termine avec succès.
4.  **`@AfterThrowing`** : S'exécute uniquement si une exception est levée, permettant de capturer et de traiter les erreurs de manière centralisée.

### Syntaxe des Pointcuts
Le projet utilise des expressions de désignation de points de jonction (PCD) comme `execution(* org.example.test.Application.main(..))`, où :
- `*` : Indique n'importe quel type de retour.
- `(..)` : Indique n'importe quel nombre et type d'arguments.

---

## Conclusion
Ce TP démontre que l'AOP n'est pas seulement un outil technique, mais une véritable philosophie de conception. En utilisant AspectJ, nous avons réussi à injecter des fonctionnalités transversales complexes (sécurité, logs, validation métier) tout en gardant un code source métier pur et focalisé sur sa responsabilité unique.
