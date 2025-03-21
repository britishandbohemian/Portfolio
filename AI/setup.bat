@echo off
setlocal enabledelayedexpansion

echo Setting up Personal QA Chatbot...

:: Create project directory
mkdir personal-qa-bot
cd personal-qa-bot

:: Create virtual environment
echo Creating virtual environment...
python -m venv venv

:: Activate virtual environment
call venv\Scripts\activate.bat

:: Install dependencies
echo Installing required packages...
pip install --upgrade pip
pip install transformers datasets torch flask pandas

:: Create data directory
mkdir data

:: Create sample personal data file
echo Creating sample personal data file...
(
echo [
echo   {"question": "Where was I born?", "answer": "I was born in [your birthplace]."},
echo   {"question": "What are my hobbies?", "answer": "My hobbies include [list your hobbies]."},
echo   {"question": "What's my favorite food?", "answer": "My favorite food is [your favorite food]."},
echo   {"question": "What did I study in school?", "answer": "I studied [your field of study]."},
echo   {"question": "Where do I work?", "answer": "I work at [your workplace/occupation]."},
echo   {"question": "Do I have any pets?", "answer": "I have [your pets or 'no pets']."},
echo   {"question": "What's my favorite movie?", "answer": "My favorite movie is [your favorite movie]."},
echo   {"question": "What music do I listen to?", "answer": "I listen to [your music preferences]."},
echo   {"question": "What languages do I speak?", "answer": "I speak [languages you speak]."},
echo   {"question": "Where have I traveled?", "answer": "I have traveled to [places you've visited]."}
echo ]
) > data\my_personal_data.json

:: Create fine-tuning script
echo Creating model fine-tuning script...
(
echo import torch
echo from transformers import AutoModelForCausalLM, AutoTokenizer, TrainingArguments, Trainer
echo from datasets import Dataset
echo import json
echo import pandas as pd
echo.
echo # Load your personal data
echo with open('data/my_personal_data.json', 'r'^) as f:
echo     data = json.load(f^)
echo.
echo # Convert to pandas DataFrame
echo df = pd.DataFrame(data^)
echo.
echo # Format the data for training 
echo def format_for_training(q, a^):
echo     return f"Question: {q}\nAnswer: {a}"
echo.
echo df['text'] = df.apply(lambda row: format_for_training(row['question'], row['answer']^), axis=1^)
echo.
echo # Create a Hugging Face dataset
echo dataset = Dataset.from_pandas(df[['text']]^)
echo.
echo # Split into training and evaluation sets
echo dataset = dataset.train_test_split(test_size=0.1^)
echo.
echo # Load a small model and tokenizer
echo model_name = "distilgpt2"
echo tokenizer = AutoTokenizer.from_pretrained(model_name^)
echo model = AutoModelForCausalLM.from_pretrained(model_name^)
echo.
echo # Make sure the tokenizer has a padding token
echo if tokenizer.pad_token is None:
echo     tokenizer.pad_token = tokenizer.eos_token
echo.
echo # Tokenize the dataset
echo def tokenize_function(examples^):
echo     return tokenizer(examples["text"], padding="max_length", truncation=True, max_length=128^)
echo.
echo tokenized_datasets = dataset.map(tokenize_function, batched=True^)
echo.
echo # Define training arguments
echo training_args = TrainingArguments(
echo     output_dir="./my-personal-model",
echo     overwrite_output_dir=True,
echo     num_train_epochs=3,
echo     per_device_train_batch_size=8,
echo     per_device_eval_batch_size=8,
echo     eval_steps=500,
echo     save_steps=500,
echo     warmup_steps=500,
echo     prediction_loss_only=True,
echo     learning_rate=5e-5,
echo ^)
echo.
echo # Create a Trainer
echo trainer = Trainer(
echo     model=model,
echo     args=training_args,
echo     train_dataset=tokenized_datasets["train"],
echo     eval_dataset=tokenized_datasets["test"],
echo ^)
echo.
echo # Fine-tune the model
echo trainer.train(^)
echo.
echo # Save the fine-tuned model
echo model.save_pretrained("./my-personal-model"^)
echo tokenizer.save_pretrained("./my-personal-model"^)
echo.
echo print("Model training complete! Model saved to ./my-personal-model"^)
) > finetune.py

:: Create Flask app
echo Creating Flask application...
(
echo from flask import Flask, request, jsonify, render_template
echo from transformers import AutoModelForCausalLM, AutoTokenizer
echo import torch
echo import os
echo.
echo app = Flask(__name__^)
echo.
echo @app.route('/'^^)
echo def home(^):
echo     return render_template('index.html'^)
echo.
echo @app.route('/check_model', methods=['GET']^^)
echo def check_model(^):
echo     if os.path.exists("./my-personal-model"^):
echo         return jsonify({"status": "ready"}^)
echo     else:
echo         return jsonify({"status": "not_ready"}^)
echo.
echo @app.route('/ask', methods=['POST']^^)
echo def ask_question(^):
echo     data = request.json
echo     question = data.get('question', ''^^)
echo     
echo     # Load your fine-tuned model (if not already loaded^)
echo     if not hasattr(app, 'model'^) or not hasattr(app, 'tokenizer'^):
echo         model_path = "./my-personal-model"
echo         app.tokenizer = AutoTokenizer.from_pretrained(model_path^)
echo         app.model = AutoModelForCausalLM.from_pretrained(model_path^)
echo     
echo     # Format the input
echo     input_text = f"Question: {question}\nAnswer:"
echo     
echo     # Tokenize
echo     inputs = app.tokenizer(input_text, return_tensors="pt"^)
echo     
echo     # Generate answer
echo     with torch.no_grad(^):
echo         output = app.model.generate(
echo             inputs["input_ids"],
echo             max_length=100,
echo             num_return_sequences=1,
echo             temperature=0.7,
echo             do_sample=True,
echo             pad_token_id=app.tokenizer.eos_token_id
echo         ^)
echo     
echo     # Decode the response
echo     response = app.tokenizer.decode(output[0], skip_special_tokens=True^)
echo     
echo     # Extract just the answer part
echo     answer = response.split("Answer:"^)[1].strip(^) if "Answer:" in response else response
echo     
echo     return jsonify({"question": question, "answer": answer}^)
echo.
echo if __name__ == '__main__':
echo     app.run(debug=True^)
) > app.py

:: Create templates directory and HTML file
mkdir templates
echo Creating HTML template...
(
echo ^<!DOCTYPE html^>
echo ^<html lang="en"^>
echo ^<head^>
echo     ^<meta charset="UTF-8"^>
echo     ^<meta name="viewport" content="width=device-width, initial-scale=1.0"^>
echo     ^<title^>Ask Me Anything^</title^>
echo     ^<style^>
echo         body {
echo             font-family: Arial, sans-serif;
echo             max-width: 600px;
echo             margin: 0 auto;
echo             padding: 20px;
echo         }
echo         .chat-container {
echo             border: 1px solid #ddd;
echo             border-radius: 8px;
echo             padding: 20px;
echo             height: 300px;
echo             overflow-y: auto;
echo             margin-bottom: 20px;
echo         }
echo         .question {
echo             background-color: #e6f7ff;
echo             padding: 10px;
echo             border-radius: 8px;
echo             margin-bottom: 10px;
echo         }
echo         .answer {
echo             background-color: #f0f0f0;
echo             padding: 10px;
echo             border-radius: 8px;
echo             margin-bottom: 10px;
echo         }
echo         .input-container {
echo             display: flex;
echo         }
echo         input {
echo             flex: 1;
echo             padding: 10px;
echo             border: 1px solid #ddd;
echo             border-radius: 4px;
echo         }
echo         button {
echo             padding: 10px 20px;
echo             background-color: #4CAF50;
echo             color: white;
echo             border: none;
echo             border-radius: 4px;
echo             margin-left: 10px;
echo             cursor: pointer;
echo         }
echo         .model-status {
echo             margin-bottom: 10px;
echo             padding: 10px;
echo             border-radius: 4px;
echo         }
echo         .status-ready {
echo             background-color: #d4edda;
echo             color: #155724;
echo         }
echo         .status-not-ready {
echo             background-color: #f8d7da;
echo             color: #721c24;
echo         }
echo     ^</style^>
echo ^</head^>
echo ^<body^>
echo     ^<h1^>Ask Me Anything^</h1^>
echo     
echo     ^<div id="model-status" class="model-status"^>Checking model status...^</div^>
echo     
echo     ^<div class="chat-container" id="chat"^>
echo         ^<div class="answer"^>Hi! I'm a personal AI trained on data about me. Ask me anything!^</div^>
echo     ^</div^>
echo     ^<div class="input-container"^>
echo         ^<input type="text" id="question" placeholder="Type your question here..."^>
echo         ^<button id="ask-button" onclick="askQuestion()"^>Ask^</button^>
echo     ^</div^>
echo 
echo     ^<script^>
echo         document.addEventListener('DOMContentLoaded', function() {
echo             checkModelStatus();
echo         });
echo         
echo         async function checkModelStatus() {
echo             try {
echo                 const response = await fetch('/check_model');
echo                 const data = await response.json();
echo                 
echo                 const statusElement = document.getElementById('model-status');
echo                 const askButton = document.getElementById('ask-button');
echo                 
echo                 if (data.status === "ready") {
echo                     statusElement.textContent = "Model is ready to answer questions!";
echo                     statusElement.className = "model-status status-ready";
echo                     askButton.disabled = false;
echo                 } else {
echo                     statusElement.textContent = "Model is not trained yet. Please run 'python finetune.py' first.";
echo                     statusElement.className = "model-status status-not-ready";
echo                     askButton.disabled = true;
echo                 }
echo             } catch (error) {
echo                 console.error('Error checking model status:', error);
echo                 const statusElement = document.getElementById('model-status');
echo                 statusElement.textContent = "Error checking model status. Please make sure the server is running.";
echo                 statusElement.className = "model-status status-not-ready";
echo             }
echo         }
echo         
echo         async function askQuestion() {
echo             const questionInput = document.getElementById('question');
echo             const question = questionInput.value;
echo             
echo             if (!question) return;
echo             
echo             // Add question to chat
echo             const chatContainer = document.getElementById('chat');
echo             const questionDiv = document.createElement('div');
echo             questionDiv.className = 'question';
echo             questionDiv.textContent = question;
echo             chatContainer.appendChild(questionDiv);
echo             
echo             // Clear input
echo             questionInput.value = '';
echo             
echo             try {
echo                 // Send request to backend
echo                 const response = await fetch('/ask', {
echo                     method: 'POST',
echo                     headers: {
echo                         'Content-Type': 'application/json'
echo                     },
echo                     body: JSON.stringify({ question })
echo                 });
echo                 
echo                 const data = await response.json();
echo                 
echo                 // Add answer to chat
echo                 const answerDiv = document.createElement('div');
echo                 answerDiv.className = 'answer';
echo                 answerDiv.textContent = data.answer;
echo                 chatContainer.appendChild(answerDiv);
echo                 
echo                 // Scroll to bottom
echo                 chatContainer.scrollTop = chatContainer.scrollHeight;
echo             } catch (error) {
echo                 console.error('Error:', error);
echo                 const errorDiv = document.createElement('div');
echo                 errorDiv.className = 'answer';
echo                 errorDiv.textContent = 'Sorry, I encountered an error. Please try again.';
echo                 chatContainer.appendChild(errorDiv);
echo             }
echo         }
echo         
echo         // Allow pressing Enter to send questions
echo         document.getElementById('question').addEventListener('keypress', function(e) {
echo             if (e.key === 'Enter') {
echo                 askQuestion();
echo             }
echo         });
echo     ^</script^>
echo ^</body^>
echo ^</html^>
) > templates\index.html

:: Create a README file
echo Creating README file...
(
echo # Personal QA Chatbot
echo.
echo This is a simple web application that uses a fine-tuned language model to answer questions about you based on your personal data.
echo.
echo ## Setup
echo.
echo 1. Make sure you have Python 3.7+ installed
echo 2. Run the setup script:
echo    ```
echo    setup.bat
echo    ```
echo.
echo ## Customizing Your Data
echo.
echo 1. Edit the `data/my_personal_data.json` file to include your own personal information
echo 2. Add as many question-answer pairs as possible for better results (aim for at least 20-30)
echo.
echo ## Training the Model
echo.
echo 1. Make sure you're in the project directory:
echo    ```
echo    cd personal-qa-bot
echo    ```
echo 2. Activate the virtual environment:
echo    ```
echo    venv\Scripts\activate
echo    ```
echo 3. Run the fine-tuning script:
echo    ```
echo    python finetune.py
echo    ```
echo 4. This process may take some time depending on your hardware
echo.
echo ## Running the Web App
echo.
echo 1. Make sure the virtual environment is activated
echo 2. Start the Flask app:
echo    ```
echo    python app.py
echo    ```
echo 3. Open your browser and go to `http://127.0.0.1:5000/`
echo.
echo ## Notes
echo.
echo - The first time you run the app, you'll need to train the model first
echo - If the model's answers aren't good enough, try adding more data or increasing the training epochs in `finetune.py`
) > README.md

:: Create a run script
echo Creating run script...
(
echo @echo off
echo Activating virtual environment...
echo.
echo call venv\Scripts\activate
echo.
echo Starting the application...
echo python app.py
) > run.bat

:: Create a train script
echo Creating train script...
(
echo @echo off
echo Activating virtual environment...
echo.
echo call venv\Scripts\activate
echo.
echo Training the model...
echo python finetune.py
) > train.bat

echo Setup complete!
echo.
echo Next steps:
echo 1. Edit data\my_personal_data.json with your personal information
echo 2. Run 'train.bat' to train the model
echo 3. Run 'run.bat' to start the web application
echo 4. Open http://127.0.0.1:5000/ in your browser
echo.
echo Press any key to exit...
pause > nul

:: Deactivate virtual environment
call venv\Scripts\deactivate.bat