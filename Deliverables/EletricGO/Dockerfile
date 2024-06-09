FROM node:20 AS build
WORKDIR /app

# Instalar o Angular CLI globalmente
RUN npm install -g @angular/cli

# Copiar o package.json e o package-lock.json e instale as dependências
COPY package*.json ./
RUN npm install

# Copiar o código fonte e construa a aplicação
COPY . .
RUN npm run prod

# Usar uma imagem base do Nginx para servir a aplicação
FROM nginx:alpine
COPY --from=build /app/dist/spa /usr/share/nginx/html

# Copiar a configuração customizada do Nginx, se houver
# COPY nginx.conf /etc/nginx/nginx.conf

# Expôr a porta padrão do Nginx
EXPOSE 80

# Comando para iniciar o Nginx
CMD ["nginx", "-g", "daemon off;"]
