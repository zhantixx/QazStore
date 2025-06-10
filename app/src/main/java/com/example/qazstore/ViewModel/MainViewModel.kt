// MainViewModel.kt
package com.example.qazstore.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qazstore.Domain.CategoryModel
import com.example.qazstore.Domain.ProductModel
import com.example.qazstore.R

class MainViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> = _categories

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> = _products

    private val _filteredProducts = MutableLiveData<List<ProductModel>>()
    val filteredProducts: LiveData<List<ProductModel>> = _filteredProducts

    init {
        loadCategories()
        loadProducts()
    }

    private fun loadCategories() {
        _categories.value = listOf(
            CategoryModel(1, "Kiım", R.drawable.cloth),
            CategoryModel(2, "Qolöner", R.drawable.qol),
            CategoryModel(3, "Kitaptar", R.drawable.books),
            CategoryModel(4, "Ashekeiler", R.drawable.glass)
        )
    }

    private fun loadProducts() {
        val allProducts = listOf(
            ProductModel(
                id = 1,
                title = "Chapan 'Nomad'",
                price = "45000 ₸",
                imageResId = R.drawable.chapan_nomad,
                brand = "Darideiya",
                description = "Qazaqtyń dástürli örnekterimen bezendirilgen chapan. Kók barhat materialynan tikilgen, kúmis árlettermen árlengen. Merzіmdіk saltanattarda kіýge arnalǵan.",
                categoryId = 1
            ),
            ProductModel(
                id = 2,
                title = "Futbolka 'Basqa'",
                price = "4990 ₸",
                imageResId = R.drawable.basqa1,
                brand = "BasqaWear",
                description = "BasqaWear usyngan zamanaýy futbolka. Ýlttyq rýhpen ornektelep, street-style dizainimen úılesken. Jılı mátemen bastyrılǵan.",
                categoryId = 1
            ),
            ProductModel(
                id = 13,
                title = "Sneaker 'Ornek'",
                price = "28900 ₸",
                imageResId = R.drawable.kross,
                brand = "KieliStep",
                description = "Últtyq ornektermen bezendirilgen zamanaýy sneaker. Aıyryqsha komfort pen dástúrdi birіktiredi, kúndelіktі kiým ushın tamasha tańdaý.",
                categoryId = 1
            ),
            ProductModel(
                id = 14,
                title = "Hoodie 'We are QR'",
                price = "15990 ₸",
                imageResId = R.drawable.qr,
                brand = "QR Clothing",
                description = "Qara tústeǵі minimalist hoodie, QR halqy ushyn arnawly dizain. Jılı, sapaly matadan tikilgen. Street-fashion fanshylary ushyn keremet tańdaý.",
                categoryId = 1
            ),

            ProductModel(
                id = 7,
                title = "Abai Joly",
                price = "3500 ₸",
                imageResId = R.drawable.abai_joly,
                brand = "Foliant",
                description = "M.Auezovtyń klassikalyq roman-epopeıasy. Abai ómіrі, aqyl-parasaty men Qazaq halqynyń dástúrі tereń surettelgen.",
                categoryId = 3
            ),
            ProductModel(
                id = 8,
                title = "Álíppe oqýlyǵy",
                price = "1990 ₸",
                imageResId = R.drawable.alipbe,
                brand = "Mektep baspasy",
                description = "Bastauysh oqushylardyń áripterdi úırenýine arnalǵan oqýlyq. Ádemі bezendіrіlgen, úırenýdi onytały etedі.",
                categoryId = 3
            ),
            ProductModel(
                id = 9,
                title = "Qazaqstan tarıhy 9-synyp",
                price = "2490 ₸",
                imageResId = R.drawable.qaz_tarih,
                brand = "Atamura",
                description = "9-synyp oqýshylaryna arnalǵan Qazaqstannyn syrlardy tarıhy. Qazaqtyń memlekettіk damý joldaryny túsіndіredі.",
                categoryId = 3
            ),
            ProductModel(
                id = 20,
                title = "Salem, Almaty!",
                price = "4900 ₸",
                imageResId = R.drawable.salem,
                brand = "AlmatyBooks",
                description = "Balalarǵa arnalǵan qalamyz Almaty turaly erekshe oqýlyq. Suretter men oqıǵalar qalamyzdyń ruhiyn tanýǵa jol ashadı.",
                categoryId = 3
            ),
            ProductModel(
                id = 21,
                title = "Qara Sózder",
                price = "2990 ₸",
                imageResId = R.drawable.abai,
                brand = "AbaıFoundation",
                description = "Abai Qunanbaevtyń aqyldy sózderі men qymbat múralary. Rýhani tazalyq pen adamgershіlіktі úıretetіn erekshe kitab.",
                categoryId = 3
            ),ProductModel(
                id = 4,
                title = "Sırğa 'Tumar'",
                price = "12000 ₸",
                imageResId = R.drawable.sirga_tumar,
                brand = "HalykZerger",
                description = "Qolmen jasalǵan kúmis sırǵa. Tumar túrinde jasalǵan, ortasynda kók jaspar tasy bar. Rýhani qorǵanystyń belgisі retinde tanylady.",
                categoryId = 2
            ),
            ProductModel(
                id = 5,
                title = "Bilezik 'Kobelek'",
                price = "46900 ₸",
                imageResId = R.drawable.bilezik_kobelek,
                brand = "HalykZerger",
                description = "Kobelek formasyndaǵy kúmis bilezik. Kók taspen bezendіrіlgen, aıelderge arnalǵan zhasampazdyqtyń belgisі.",
                categoryId = 2
            ),
            ProductModel(
                id = 18,
                title = "Áshékéi 'Kók köz'",
                price = "32500 ₸",
                imageResId = R.drawable.ashekei1,
                brand = "Qoloner.kz",
                description = "Komplekt kúmis ashekeı: sırǵa, bilezik, alqa. Tásýl kók taspen bezendіrіlgen, dástür men zhamandylardy qorǵau niyetіmen jasalǵan.",
                categoryId = 2
            ),
            ProductModel(
                id = 19,
                title = "Áshékéi 'Túıın'",
                price = "28800 ₸",
                imageResId = R.drawable.ashekei2,
                brand = "QazZher",
                description = "Zamanagóy kúmis ashekeı tolyq zhinagymen. Táńý taspa men kúmis detaldarmen bezendіrіlgen. Saltpen úılesken.",
                categoryId = 2
            ),
            ProductModel(
                id = 22,
                title = "Sırǵa 'Kanattar'",
                price = "26245 ₸",
                imageResId = R.drawable.sirga_kanattar,
                brand = "HalykZerger",
                description = "Qanat formasymen jasalǵan kúmis sırǵa. Erkіndіktіń symboly bolyp sanalady. Últtyq sәn men azat rýhdі úılesіmde sýretteydі.",
                categoryId = 2
            ),
            ProductModel(
                id = 10,
                title = "Galstuk 'Shoqan'",
                price = "12000 ₸",
                imageResId = R.drawable.shoqan,
                brand = "Adili",
                description = "Shoqan ýaqyt men zamanaýy stıl úılesken oramal. Qazaqtyń aqyldy bolmysy men tarihi obrazyn kúrep beredі.",
                categoryId = 4
            ),
            ProductModel(
                id = 11,
                title = "Shopper 'Qumai'",
                price = "18000 ₸",
                imageResId = R.drawable.shopper1,
                brand = "Adili",
                description = "Bylǵarydan jasalǵan shopping-sumka. Íshіnede kop zat syatyn keń, sapaly, kún delіktі paydalaný ushyn.",
                categoryId = 4
            ),
            ProductModel(
                id = 12,
                title = "Shopper 'Jibek jolı'",
                price = "10000 ₸",
                imageResId = R.drawable.shopper2,
                brand = "Adili",
                description = "Jibekten, kúni motifpen bezendіrіlgen. Áıelderǵe arnalǵan záń shynyndagy kombinasıasy.",
                categoryId = 4
            ),
            ProductModel(
                id = 23,
                title = "Takıa 'Kók Zher'",
                price = "8700 ₸",
                imageResId = R.drawable.takia1,
                brand = "Zher Clothing",
                description = "Kók barhatpen qaptalǵan, kúmis ornamentpen árlenǵen últtyq bas kım. Qazaq dástúrіn úılesіmdі jyludan kórsetedі.",
                categoryId = 4
            ),
            ProductModel(
                id = 24,
                title = "Takıa 'Shyńǵyr'",
                price = "8700 ₸",
                imageResId = R.drawable.takia2,
                brand = "Shabyt",
                description = "Táńýly kúmis jyltyraq detal'darmen bezendіrіlgen aqsha-kók takıa. Dästúrli men stil úılesіmі.",
                categoryId = 4
            )



        )
        _products.value = allProducts
        _filteredProducts.value = allProducts
    }

    fun selectCategory(categoryId: Int) {
        val allProducts = _products.value ?: return
        _filteredProducts.value = allProducts.filter { it.categoryId == categoryId }
    }
}
