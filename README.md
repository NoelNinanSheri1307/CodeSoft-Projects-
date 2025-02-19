# 🚀 CodeSoft Projects  
## 📝 Developed by Noel Ninan Sheri  

This repository contains two Android applications developed as part of my internship with **CodSoft**:  

1. **TaskListApp** - A simple yet functional task management app.  
2. **QuoteOfTheDayApp** - An app that provides daily inspirational quotes with added functionalities.  

---  

## 🛠️ Tech Stack & Tools  
- **Languages:** Kotlin  
- **IDE:** Android Studio  
- **Version Control:** Git & GitHub  
- **Platform:** Android  

---  

## 📱 **1. TaskListApp**  

### 📝 **Description:**  
TaskListApp helps users manage their daily tasks efficiently. Users can:  
✅ Add new tasks  
✅ Edit and update existing tasks  
✅ Delete tasks  
✅ Set task priorities  

### 🌟 **Features:**  
- **Add Tasks:** Add a task with a name and priority level.  
- **Edit Tasks:** Update task details when needed.  
- **Delete Tasks:** Remove completed or unwanted tasks.  
- **Priority Levels:** Categorize tasks as High, Medium, or Low.  
- **User Feedback:** Displays an appropriate message when a task name is left empty.  
- **Enhanced UI/UX:** Clean interface with smooth user interactions.  

### 📂 **Project Structure:**  
TaskListApp/
├── app/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/com/example/tasklistapp/
│ │ │ │ ├── MainActivity.java
│ │ │ │ ├── Task.java
│ │ │ │ └── TaskAdapter.java
│ │ │ └── res/layout/
│ │ │ ├── activity_main.xml
│ │ │ └── item_task.xml
│ │ └── res/values/ (colors, strings, themes)
└── build.gradle


### 🧩 **Code Explanation:**  
- **MainActivity.java:** Handles task addition, deletion, and updates.  
- **Task.java:** Data class representing a task.  
- **TaskAdapter.java:** Adapter for rendering the task list with priority indicators.  

### 📸 **Demo Screenshots:**  
> *(Add your screenshots here)*  

---

## 📖 **2. QuoteOfTheDayApp**  

### 📝 **Description:**  
QuoteOfTheDayApp delivers daily inspirational quotes with options to:  
✅ Generate random quotes  
✅ Save favorite quotes  
✅ Share quotes via other apps  
✅ View saved quotes  

### 🌟 **Features:**  
- **New Quote:** Randomizes a new quote at the press of a button.  
- **Save Quote:** Allows users to save quotes for future reference.  
- **Share Quote:** Easily share quotes through messaging and social apps.  
- **View Saved Quotes:** Access and manage saved quotes.  
- **Improved UI/UX:** Blue and white theme with distinct button colors.  

### 🎨 **Button Colors:**  
- 🟥 **New Quote:** Red  
- 🟦 **Save Quote:** Blue  
- 🟩 **Share Quote:** Green  
- 🟪 **View Saved Quotes:** Purple  

### 📂 **Project Structure:**  
QuoteOfTheDayApp/
├── app/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/com/example/quoteoftheday/
│ │ │ │ ├── MainActivity.java
│ │ │ │ ├── SavedQuotesActivity.java
│ │ │ │ └── QuoteStorage.java
│ │ │ └── res/layout/
│ │ │ ├── activity_main.xml
│ │ │ └── activity_saved_quotes.xml
│ │ └── res/font/myfont.ttf (Custom font)
└── build.gradle

### 🧩 **Code Explanation:**  
- **MainActivity.java:** Handles quote randomization, saving, and sharing.  
- **SavedQuotesActivity.java:** Displays a list of saved quotes.  
- **QuoteStorage.java:** Manages quote storage using SharedPreferences.  



## 🏆 **Installation & Usage**  
1. Clone the repository:  
   ```bash
   git clone https://github.com/NoelNinanSheri1307/CodeSoft-Projects-.git
Open Android Studio and import the desired app folder.
Build and run the project on an emulator or physical device.
🙌 Acknowledgements
Self-driven learning and exploration during my internship with CodSoft helped me understand how much I can achieve in a self-motivating environment.
Developed With Curiosity By Noel Ninan Sheri
