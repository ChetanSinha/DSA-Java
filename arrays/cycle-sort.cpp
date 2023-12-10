#include <bits/stdc++.h>
using namespace std;


void func(vector<int>&arr, int n) {
	int i=0;

	while (i<n) {
		while (i+1 != arr[i]) {
			swap(arr[i], arr[arr[i]-1]);
		} 
		i += 1;
	}
}



int main() {

	int t;
	cin >> t;

	while (t--) {
		int n;
		cin >> n;
		vector<int> arr(n);

		for (auto &a : arr) {
			cin >> a;
		}

		func(arr, n);


		for (auto &a : arr) {
			cout << a << " ";
		}
		cout << endl;
	}
} 