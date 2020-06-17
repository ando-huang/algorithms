#include <bits/stdc++.h>
#include <stdio.h>
#include <vector>
#include <iostream>

using namespace std;

string ltrim(const string &);
string rtrim(const string &);
vector<string> split(const string &);

/*
    Modulo function to reduce overly large numbers and negatives
*/
int mod(int l){
    if (l > 1000000009){
        return l%1000000009;
    }
    else{
        return l;
    }
}

/**
    gradeschool multiplication for arrays of small size(less than 16)
**/
vector<long> gradeSchool(vector<long> a, vector<long> b){
    vector<long> h;
    //long* harr = (long*)malloc((a.size()+b.size()-1)*sizeof(long));
    for(int i = 0; i<(a.size()+b.size()-1); i++){
        h[i] = 0;
    }
    for(int i = 0; i<a.size(); i++){
        for(int j = 0; j<b.size(); j++){
            h[i+j] += b[i]*a[i];
        }
    }

    return h;
}

/*
 * Complete the 'smellCosmos' function below.
 *
 * The function is expected to return a LONG_INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. LONG_INTEGER_ARRAY a
 *  2. LONG_INTEGER_ARRAY b
 */

vector<long> smellCosmos(vector<long> a, vector<long> b) {
    // Write your code here
    if(a.size() < 16){
        return gradeSchool(a, b);
    }

    vector<long> h;

    //actual karatsuba implementation
    vector<long> aHigh;
    vector<long> aLow;
    vector<long> bHigh;
    vector<long> bLow;
    vector<long> hMid;
    return h;

}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string n_temp;
    getline(cin, n_temp);

    int n = stoi(ltrim(rtrim(n_temp)));

    string a_temp_temp;
    getline(cin, a_temp_temp);

    vector<string> a_temp = split(rtrim(a_temp_temp));

    vector<long> a(n + 1);

    for (int i = 0; i < n + 1; i++) {
        long a_item = stol(a_temp[i]);

        a[i] = a_item;
    }

    string b_temp_temp;
    getline(cin, b_temp_temp);

    vector<string> b_temp = split(rtrim(b_temp_temp));

    vector<long> b(n + 1);

    for (int i = 0; i < n + 1; i++) {
        long b_item = stol(b_temp[i]);

        b[i] = b_item;
    }

    vector<long> result = smellCosmos(a, b);

    for (int i = 0; i < result.size(); i++) {
        fout << result[i];

        if (i != result.size() - 1) {
            fout << " ";
        }
    }

    fout << "\n";

    fout.close();

    return 0;
}

string ltrim(const string &str) {
    string s(str);

    s.erase(
        s.begin(),
        find_if(s.begin(), s.end(), not1(ptr_fun<int, int>(isspace)))
    );

    return s;
}

string rtrim(const string &str) {
    string s(str);

    s.erase(
        find_if(s.rbegin(), s.rend(), not1(ptr_fun<int, int>(isspace))).base(),
        s.end()
    );

    return s;
}

vector<string> split(const string &str) {
    vector<string> tokens;

    string::size_type start = 0;
    string::size_type end = 0;

    while ((end = str.find(" ", start)) != string::npos) {
        tokens.push_back(str.substr(start, end - start));

        start = end + 1;
    }

    tokens.push_back(str.substr(start));

    return tokens;
}
