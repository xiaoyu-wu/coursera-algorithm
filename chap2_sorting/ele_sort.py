from template_sort_class import Comparable, Example


class ComparableChar(Comparable):

    value = 0

    def __init__(self, value):
        self.value = value

    def __repr__(self):
        return self.value

    def compare_to(self, other):
        if self.value > other.value:
            return 1
        elif self.value == other.value:
            return 0
        else:
            return -1


class SelectionSort(Example):

    def sort(a):
        for i in range(len(a)):
            min_idx = i
            for j in range(i, len(a)):
                if not Example.less(a[min_idx], a[j]):
                    min_idx = j
            Example.exchange(a, i, min_idx)


if __name__ == "__main__":
    input_string = "S O R T E X A M P L E"
    a = []
    for i in input_string.split():
        a.append(ComparableChar(i))
    SelectionSort.sort(a)
    SelectionSort.show(a)
    print("Sorted: {}".format(SelectionSort.is_sorted(a)))
