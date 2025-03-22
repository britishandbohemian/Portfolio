import torch
from transformers import AutoModelForCausalLM, AutoTokenizer, TrainingArguments, Trainer
from datasets import Dataset
import json
import pandas as pd

# Load your personal data
with open('data/my_personal_data.json', 'r') as f:
    data = json.load(f)

# Convert to pandas DataFrame
df = pd.DataFrame(data)

# Format the data for training 
def format_for_training(q, a):
    return f"Question: {q}\nAnswer: {a}"

df['text'] = df.apply(lambda row: format_for_training(row['question'], row['answer']), axis=1)

# Create a Hugging Face dataset
dataset = Dataset.from_pandas(df[['text']])

# Split into training and evaluation sets
dataset = dataset.train_test_split(test_size=0.1)

# Load a small model and tokenizer
model_name = "distilgpt2"
tokenizer = AutoTokenizer.from_pretrained(model_name)
model = AutoModelForCausalLM.from_pretrained(model_name)

# Make sure the tokenizer has a padding token
if tokenizer.pad_token is None:
    tokenizer.pad_token = tokenizer.eos_token

# Tokenize the dataset
def tokenize_function(examples):
    return tokenizer(examples["text"], padding="max_length", truncation=True, max_length=128)

tokenized_datasets = dataset.map(tokenize_function, batched=True)

# Define training arguments
training_args = TrainingArguments(
    output_dir="./my-personal-model",
    overwrite_output_dir=True,
    num_train_epochs=3,
    per_device_train_batch_size=8,
    per_device_eval_batch_size=8,
    eval_steps=500,
    save_steps=500,
    warmup_steps=500,
    prediction_loss_only=True,
    learning_rate=5e-5,
)

# Create a Trainer
trainer = Trainer(
    model=model,
    args=training_args,
    train_dataset=tokenized_datasets["train"],
    eval_dataset=tokenized_datasets["test"],
)

# Fine-tune the model
trainer.train()

# Save the fine-tuned model
model.save_pretrained("./my-personal-model")
tokenizer.save_pretrained("./my-personal-model")

print("Model training complete Model saved to ./my-personal-model")
