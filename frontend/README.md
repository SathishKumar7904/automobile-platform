# Automobile Digital Platform — Angular MVP

Phase 3 frontend for Dealer Lead Management.

## Stack

- Angular 22
- TypeScript
- HTML5
- CSS3
- Angular Router
- Angular Forms
- Angular HttpClient
- REST/JSON

## MVP flows

### Customer

1. Select brand, model, variant and dealer.
2. Submit an enquiry.
3. View submitted enquiries.
4. Open enquiry details and track the current lead status.

### Dealer

1. Open the dealer dashboard.
2. View leads scoped to a dealer ID.
3. Open a lead.
4. Progress the lead through the backend transition policy.
5. Add an internal note.
6. View status history.

## Local development

The Angular development proxy routes requests to the existing local Spring Boot services:

- Vehicle service → `localhost:8081`
- Dealer service → `localhost:8082`
- Enquiry service → `localhost:8083`

Run:

```bash
npm install
ng serve
```

Then open `http://localhost:4200`.

## Demo IDs

The initial UI values use the Phase 3 integration data already used during backend verification. The customer and dealer IDs can be changed directly in their respective screens.

## Security note

The current backend branch does not yet contain the OAuth2/OIDC/JWT security implementation described by the Phase 2 architecture. The MVP UI therefore does not pretend that the editable demo IDs are authentication. Real authentication/authorization must be wired into the existing security architecture before production use.
