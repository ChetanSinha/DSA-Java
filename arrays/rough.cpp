#include <bits/stdc++.h>
using namespace std;


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