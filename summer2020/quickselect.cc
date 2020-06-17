#include <bits/stdc++.h>
#include <cstdlib>

using namespace std;

int partition(int arr[], int l, int r, int x) {
  int i;
  for (i = l; i < r; i++)
    if (arr[i] == x)
      break;
  swap(arr[i], arr[r]);
  i = l;
  for (int j = l; j <= r - 1; j++) {
    if (arr[j] <= x) {
      swap(arr[i], arr[j]);
      i++;
    }
  }
  swap(arr[i], arr[r]);
  return i;
}

int sortedMedian(int arr[], int n) {
  sort(arr, arr + n);
  return arr[n / 2];
}

int doQuickselect(int arr[], int l, int r, int k) {
  // only care if we are in the range
  if (k > 0 && k <= r - l + 1) {
    int n = r - l + 1;
    int i, median[(n + 4) / 5];
    for (i = 0; i < n / 5; i++)
      median[i] = sortedMedian(arr + l + i * 5, 5);
    if (i * 5 < n) {
      median[i] = sortedMedian(arr + l + i * 5, n % 5);
      i++;
    }
    int medOfMed =
        (i == 1) ? median[i - 1] : doQuickselect(median, 0, i - 1, i / 2);
    int pos = partition(arr, l, r, medOfMed);
    if (pos - l == k - 1)
      return arr[pos];
    else if (pos - l > k - 1)
      return doQuickselect(arr, l, pos - 1, k);
    else
      return doQuickselect(arr, pos + 1, r, k - pos + l - 1);
  }
  return INT_MIN; // we are out of range
}

int quickselect(int arr[], int n, int k) {
  return doQuickselect(arr, 0, n - 1, k);
}

int arr[10000];

int main() {
  srand(time(0));
  int n, k;
  cin >> n >> k;
  for (int i = 0; i < n; i++)
    arr[i] = i + 1;
  random_shuffle(arr, arr + n);
  for (int i = 0; i < n; i++)
    cout << arr[i] << ((i == n - 1) ? "\n" : ", ");
  cout << "K'th smallest element is " << quickselect(arr, n, k) << endl;
  cout << "Actual k'th element is " << k << endl;
  return 0;
}