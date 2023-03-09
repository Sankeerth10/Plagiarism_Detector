# Plagiarism_Detector
Plagiarism detector to detect the similarity between 2 text files. It leverages lcs algorithm for comparison.

Input: 2 Text Files
Output: "1", if they are plagiarized, "0" otherwise

Steps of the Algorithm:
The contents of both the text files are read line by line using the buffereader.

The words of the file are stored and preprocessing techniques are applied. These include removing white spaces, replacing the punctuations with empty strings, removing the stop words, extracting the root words by stemming, removing duplicate words and sorting the words. 

White spaces and punctuations are removed using regex.A list of stop words is used for removing stop words from the arrays of words of 2 text files. Duplicated words are removed by using set data structure. Sorting of the words is done by using the Collections.Sort method.

Once all the preprocessing is done, we pass both the array of words of the 2 files into the modifiedLcs method. This is an optimized version of lcs where the whole 2D matrix of the order of word Array 1 and word Array 2 is not stored. This is because, when the size of the text files is large, we may run into an out of memory error.

In this optimized version of lcs, only the values of the columns and the previous row are stored as at any instance of time, only the values of the columns and the previous row is required in the algorithm. This reduces the size of the array required to store the lcs values from [M+1] x [N+1] to [2] x [N+1] where M AND N are the size of the word arrays. The space complexity is reduced from O(MN) to O(N).

The value returned from the modified lcs method is used in classifying the pair of files as plagiarised or not plagiarised. The value of lcs upon the minimum of the lengths of both the word arrays is the similarity ratio and is used for comparision with a threshold value.

The value of threshold is set as 0.63. We compare the value obtained from the above step(similarity ratio) with this threshold to return "0" or "1". 

If the value obtained is less than 0.63 then, we return "0" i.e, no plagiarism is detected.

If the value obtained is more than 0.63 then, we return "1" i.e, plagiarism is detected.
