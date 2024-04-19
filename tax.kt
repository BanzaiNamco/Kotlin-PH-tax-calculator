class MonthlyContributions(income : Double){
    //all are val because they dont need to change
    private val pagIbig : Double = when {
        income <= 1500 -> income * 0.01
        income > 5000 -> 5000*0.02
        else -> income * 0.02
    }
    private val philHealth : Double = when {
        income < 10000.00 -> 225.00
        income in 10000.00..89999.99 -> (income * 0.045)/2
        else -> 4050.00
    }
    private val sss : Double = getSSSBracket(income) * 0.045

    //getter auto constructed
    //since it is a val, there is no setter
    val total = pagIbig + philHealth + sss

    fun getSSSBracket(income: Double) : Double{
        return when {
            income >= 29750.00 -> 30000.00
            income < 4250.00 -> 4000.00
            else -> (String.format("%.0f", income / 500).toDouble() * 500)
        }
        return 0.04
    }
    //display monthly contri
    fun display(){
        println("-----------Monthly Contributions----------")
        println("SSS ₱" + String.format("%,.2f",sss))
        println("PhilHealth ₱" + String.format("%,.2f",philHealth))
        println("PagIbig ₱" + String.format("%,.2f",pagIbig))
        println("Total ₱" + String.format("%,.2f",total))
    }
}
class TaxComp(income : Double){
    //val because the value does not change
    //getter auto-constructed, val does not have a setter
    val withholdingTax: Double = when {
        income < 20833.00 -> 0.00
        income in 20833.00..33332.00 -> (income - 20833) * 0.15
        income in 33333.00..66666.00 -> (income - 33333) * 0.20 + 1875.00
        income in 66667.00..166666.00 -> (income - 66667) * 0.25 + 8541.80
        income in 16667.00..666666.00 -> (income - 166667) * 0.30 + 33541.80
        else -> (income - 666667) * 0.35 + 183541.80
    }
    //display tax comp
    fun display(income: Double){
        println("--------------Tax Computation--------------")
        println("Income Tax: ₱" + String.format("%,.2f", withholdingTax))
        println("Net Pay after Tax : ₱ " + String.format("%,.2f",income-withholdingTax))
    }
}
fun main() {
    while (true) {
        print("Enter your monthly income: ")
        val income = readln().toDouble()
        if (income > 0) {
            val month = MonthlyContributions(income)
            val tax = TaxComp(income - month.total)

            month.display()
            tax.display(income)
            println("---------------Final Result----------------")
            println("Total Deductions: ₱" + String.format("%,.2f", month.total + tax.withholdingTax))
            println("Net Pay after Deductions: ₱" + String.format("%,.2f", income - month.total - tax.withholdingTax))
        }
        else{
            println("Invalid income")
        }
        println("\nExit Program? (0 - NO; 1 - YES)")
        if (readln() == "1")
            break
    }
}