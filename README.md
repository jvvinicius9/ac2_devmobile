
# 📲 Remédios - App de Controle de Medicamentos

Este aplicativo Android permite que usuários cadastrem, acompanhem e recebam notificações sobre o consumo dos seus medicamentos. Foi desenvolvido como parte de uma atividade da faculdade, utilizando Java e SQLite no Android Studio.

---

## 🚀 Funcionalidades

- ✅ Cadastro de medicamentos com nome, horário e descrição
- 🕓 Notificação automática na hora do consumo
- 📋 Lista de medicamentos cadastrados
- 👆 Marcar como "consumido"
- 🗑️ Excluir medicamento
- 🔽 Mostrar ou esconder a descrição do medicamento
- 🌐 Suporte a português e inglês (internacionalização)

---

## 🛠️ Tecnologias utilizadas

- Java
- Android Studio
- SQLite (banco de dados local)
- AlarmManager + BroadcastReceiver (para notificações)
- Notificações via `NotificationManager`
- Internacionalização via `strings.xml`


## 📦 Como executar o projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/seuusuario/remedios.git
   ```
2. Abra no Android Studio
3. Execute em um emulador ou dispositivo físico com Android 8.0 ou superior
4. Conceda permissão para notificações (Android 13+)

---

## ✍️ Autor

- João Vinicius – _Desenvolvedor e estudante de ADS_

---

## 📌 Observações

- O app não armazena os dados em nuvem.
- As notificações são agendadas com base na hora atual para fins de teste (5 segundos após o cadastro do novo medicamento).

---

## 🧠 Aprendizados

Este projeto foi ideal para reforçar conhecimentos em:
- Manipulação de banco de dados local (SQLite)
- Ciclo de vida de Activities
- Integração de serviços e notificações no Android

---
