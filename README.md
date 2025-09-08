# Text Generation Model

A simple **Java application that generates text based on word patterns** learned from a dataset.

## How it works

The program reads a dataset of chat messages and builds a predictive model that can generate text starting from a given word. It uses the frequency of word pairs to predict what word should come next.

## How to use

1. Compile the Java files
2. Run the Main class
3. Enter a word when prompted
4. The program will generate a sentence starting with your word

![immagine](images/image_1757331534858.gif)

## Dataset

The training data consists of casual chat messages in Italian and English, including common phrases like:
- "sto studiando"
- "torno subito"
- "ciao come stai"
- "see you later"

## TODO

- Add support for multiple sentence generation
- Implement better text preprocessing (handle punctuation, case sensitivity)
- Add confidence scores for predictions
- Implement n-gram models (bigrams, trigrams) for better predictions

## Technical notes

The model uses a simple **Markov chain approach where each word maps to possible next words with their frequencies**. When generating text, it picks the most frequent next word, or randomly selects among words with equal frequency.