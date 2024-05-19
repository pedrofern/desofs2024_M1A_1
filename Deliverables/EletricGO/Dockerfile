# Use uma imagem base do Node.js para construir a aplicação
FROM node:20 AS build
WORKDIR /app

# Instale o Angular CLI globalmente
RUN npm install -g @angular/cli

# Copie o package.json e o package-lock.json e instale as dependências
COPY package*.json ./
RUN npm install

# Copie o código fonte e construa a aplicação
COPY . .
RUN ng build --prod

# Use uma imagem base do Nginx para servir a aplicação
FROM nginx:alpine
COPY --from=build /app/dist/ /usr/share/nginx/html

# Copie a configuração customizada do Nginx, se houver
# COPY nginx.conf /etc/nginx/nginx.conf
