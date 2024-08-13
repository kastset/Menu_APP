package com.example.test.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Dish(
    val id: Int,
    val name: String,
    val type: String,
    @Serializable(with = UrlEncodedStringSerializer::class)
    val imageUrl: String,
    @Serializable(with = UrlEncodedStringSerializer::class)
    val recipeLink: String,
    var isFavorite: Boolean = false
) : Parcelable

var idNumber: Int = 1
var dishPost: Int = 3

val dishes =
    listOf(
        Dish(
            id = idNumber++,
            name = "Сливочный супчик с красной рыбкой",
            type = "Супы",
            imageUrl = "https://static.1000.menu/res/640/img/content-v2/54/8b/43142/sup-iz-krasnoi-ryby-so-slivkami_1645865140_0_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Сливочный супчик",
            type = "Супы",
            imageUrl = "https://i.obozrevatel.com/food/recipemain/2019/3/4/we.jpg?",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Грибной суп",
            type = "Супы",
            imageUrl = "https://static.1000.menu/res/640/img/content-v2/78/2a/18882/legkii-gribnoi-sup-s-vermishelu_1603697003_15_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Грибной крем - суп",
            type = "Супы",
            imageUrl = "https://klopotenko.com/wp-content/uploads/2018/03/gribnoi-krem-sup_siteweb-1000x600.jpg?",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Том Ям",
            type = "Супы",
            imageUrl = "https://klopotenko.com/wp-content/uploads/2018/03/tom-yam_siteweb-1-1000x600.jpg?",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Куриный крем - суп",
            type = "Супы",
            imageUrl = "https://static.1000.menu/img/content-v2/8e/95/42428/kurinyi-krem-sup-na-bulone-so-slivkami_1618076529_7_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Рамэн",
            type = "Супы",
            imageUrl = "https://klopotenko.com/wp-content/uploads/2023/12/ramen-z-kurkoyu-img-1000x600.jpg?",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Грибной крем - суп",
            type = "Супы",
            imageUrl = "https://klopotenko.com/wp-content/uploads/2018/11/Krem-sup-iz-shampinionov_site-web.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Натуральный лимонад",
            type = "Напитки",
            imageUrl = "https://klopotenko.com/wp-content/uploads/2018/05/Limonad-s-tarhunom_siteNewWeb.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Сливочное пиво",
            type = "Напитки",
            imageUrl = "https://cdn.segodnya.ua/i/image_1080x/media/image/61b/ddf/495/61bddf495e31c.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Смузи",
            type = "Напитки",
            imageUrl = "https://golden-flamingo.com.ua/wp-content/uploads/2023/06/5-frozen-fruit-smoothies-copy.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Лимонад из киви и цитрусов",
            type = "Напитки",
            imageUrl = "https://i.ytimg.com/vi/4yZYam3u8DY/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Горячий шоколад со взбитыми сливками",
            type = "Напитки",
            imageUrl = "https://img.freepik.com/premium-photo/hot-chocolate-with-whipped-cream_90258-2231.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Салат с сухариками",
            type = "Салаты",
            imageUrl = "https://ukr.media/static/ba/aimg/3/8/2/382470_1.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Вкусный салат из 4-х ингредиентов",
            type = "Салаты",
            imageUrl = "https://optim.tildacdn.one/tild6365-3832-4332-a565-313839623566/-/resize/744x/-/format/webp/1_.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Шампиньоны на гарнир",
            type = "Салаты",
            imageUrl = "https://i.ytimg.com/vi/z2UbCM_l6jE/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Полезный салат за копейки",
            type = "Салаты",
            imageUrl = "https://static.1000.menu/img/content-v2/78/43/34429/salat-svekla-s-chesnokom-i-greckim-orexom_1640164034_11_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Маринованные шампиньоны",
            type = "Салаты",
            imageUrl = "https://static.1000.menu/img/content-v2/bf/dd/4342/marinovannye-shampinony_1589539785_7_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Салат с сухариками № 2",
            type = "Салаты",
            imageUrl = "https://static.1000.menu/img/content-v2/c4/af/13180/salat-fasol-suxariki-kolbasa_1588657080_9_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + ((dishPost++) - 21 + dishPost++).toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Маринованные шампиньоны № 2",
            type = "Салаты",
            imageUrl = "https://treska.com.ua/wp-content/uploads/2018/03/IMG_0670got.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Ленивые сосиски в тесте",
            type = "Закуски",
            imageUrl = "https://i.ytimg.com/vi/Ij6Iz5opX_8/sddefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Сендвич в хлебе",
            type = "Закуски",
            imageUrl = "https://sovkusom.ru/wp-content/uploads/recepty/s/sendvich-v-hlebe/thumb.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Сухарики",
            type = "Закуски",
            imageUrl = "https://static.1000.menu/img/content-v2/6c/a1/4757/chesnochnye-suxariki-s-chesnokom_1604986909_17_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Ленивый сендвич",
            type = "Завтрак",
            imageUrl = "https://i.pinimg.com/736x/f6/17/17/f61717905a0bf7235e3891f07f30e7fc.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Сендвич",
            type = "Закуски",
            imageUrl = "https://static.1000.menu/img/content-v2/af/5d/38399/sendvich-s-vetchinoi_1616239118_15_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
        ),
        Dish(
            id = idNumber++,
            name = "Сендвич с яйцом",
            type = "Закуски",
            imageUrl = "https://i.obozrevatel.com/food/recipemain/2019/3/19/po1.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Сырные крокеты",
            type = "Закуски",
            imageUrl = "https://i.obozrevatel.com/food/recipemain/2019/12/23/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Треугольники",
            type = "Закуски",
            imageUrl = "https://static.1000.menu/img/content-v2/97/e6/40973/treugolniki-iz-lavasha-s-syrom-na-skovorode_1616058344_13_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Картофель фри без жарки в масле",
            type = "Закуски",
            imageUrl = "https://i.ytimg.com/vi/KrIdkF3qE1g/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Бургеры",
            type = "Закуски",
            imageUrl = "https://mliesl.edu/contents/wp-content/uploads/2022/11/image5.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Крабовые снеки",
            type = "Закуски",
            imageUrl = "https://i.lefood.menu/wp-content/uploads/w_images/2021/09/recept-6831-1240x827_w.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Закуска",
            type = "Закуски",
            imageUrl = "https://2recepta.com/recept/grenki-s-chesnokom-i-syrom/grenki-s-chesnokom-i-syrom.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Картошка фри",
            type = "Закуски",
            imageUrl = "https://img.delo-vcusa.ru/2023/02/kartoshka-fried-kak-v-mcdonalds.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Крабовые шарики",
            type = "Закуски",
            imageUrl = "https://static.1000.menu/img/content-v2/61/8b/471/syrnye-shariki-iz-krabovyx-palochek_1662097749_15_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Картошка в панировке",
            type = "Закуски",
            imageUrl = "https://i.ytimg.com/vi/27P5HNX3DQ0/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Рулетики из баклажанов",
            type = "Закуски",
            imageUrl = "https://static.1000.menu/img/content-v2/a7/16/13812/ruletiki-iz-baklajanov-s-orexami-i-syrom_1628838164_13_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Рулет с красной рыбкой",
            type = "Закуски",
            imageUrl = "https://static.1000.menu/res/640/img/content-v2/23/b7/40265/rulet-iz-ryby-s-syrom_1572526888_7_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Шампиньоны на гарнир",
            type = "Закуски",
            imageUrl = "https://i.ytimg.com/vi/VnzFusnT9wo/hq720.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Горячий бутерброд на максималках",
            type = "Закуски",
            imageUrl = "https://i.ytimg.com/vi/pH-KeghviVg/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Грибное сливочное масло",
            type = "Закуски",
            imageUrl = "https://i.ytimg.com/vi/n9zYVyzXMYI/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + (dishPost++) + ((dishPost++) - 44).toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Домашний хлеб «Чиабатта»",
            type = "Закуски",
            imageUrl = "https://i.ytimg.com/vi/aIbWI6oUW18/hq720.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Ленивая пицца",
            type = "Закуски",
            imageUrl = "https://static.1000.menu/img/content/35272/mini-picca-na-batone-v-duxovke_1559062077_11_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Лаваш с начинками",
            type = "Закуски",
            imageUrl = "https://i.ytimg.com/vi/J0Eb30LaznU/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + ((dishPost++) + (dishPost++) + (dishPost++) + (dishPost++) + (dishPost++) - 193).toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Сэндвич из сыра и бекона",
            type = "Закуски",
            imageUrl = "https://i.ytimg.com/vi/fd1ylR80198/sddefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/" + dishPost++.toString(),
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Хлопковый бисквит",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/79/3f/20353/yaponskii-biskvit-kastella_1613028183_20_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/124",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Шоколадный десерт",
            type = "Десерты",
            imageUrl = "https://i.obozrevatel.com/food/recipemain/2023/6/7/848ed8e2-2072-45d4-b8e9-2ccab4406343.jpg",
            recipeLink = "https://t.me/obedkakdoma/126",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Испанский чизкейк",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/a8/cf/50324/ispanskii-chizkeik-svyatogo-sebastyana_1600538345_12_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/128",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Лимонный пирог",
            type = "Десерты",
            imageUrl = "https://images.unian.net/photos/2020_05/thumb_files/400_0_1589971479-7095.jpg",
            recipeLink = "https://t.me/obedkakdoma/129",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Банановый пудинг",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/d6/12/61623/bananovyi-puding_1641414107_15_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/130",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Творожная запеканка с смородиной",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/2Zm9c6RgRgU/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/131",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Имбирные пряники",
            type = "Десерты",
            imageUrl = "https://yasensvit.ua/uploads/recipes/prev/634d8825d8bb7.jpg",
            recipeLink = "https://t.me/obedkakdoma/132",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Имбирные пряники №2",
            type = "Десерты",
            imageUrl = "https://img.moyo.ua/img/news_desc/1671/167162_1639739162_0.jpg",
            recipeLink = "https://t.me/obedkakdoma/133",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Мандариновая «Панна - Кота»",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/9mRQAcQjq7E/hqdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/135",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Чизкейк «Нью - Йорк»",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/res/640/img/content-v2/2e/63/26665/chizkeik-nu-iork-klassicheskii_1630161417_0_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/136",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Кекс на сгущенке",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content/10583/keksik-na-sgushchenke-snezhinka-_1368640645_0_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/137",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Чизкейк «Нью - Йорк» №2",
            type = "Десерты",
            imageUrl = "https://art-lunch.ru/content/uploads/2014/08/cheesecake-new-york-001x2-1.jpg",
            recipeLink = "https://t.me/obedkakdoma/138",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Шоколадные блинчики 3 в 1",
            type = "Десерты",
            imageUrl = "https://cdn.informator.ua/@prod/media/dnipro/2022/08/08/62f0ddbe2e211.jpg",
            recipeLink = "https://t.me/obedkakdoma/139",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Львівський сирник",
            type = "Десерты",
            imageUrl = "https://klopotenko.com/wp-content/uploads/2022/04/lvivskyy-syrnyk_sitewebukr-1000x600.jpg",
            recipeLink = "https://t.me/obedkakdoma/143",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Творожные сырки",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/RxamcXMi1yI/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/147",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Блины «Рафаэло»",
            type = "Десерты",
            imageUrl = "https://ukranews.com/upload/news/2020/02/28/285e595822013b1-5e595811ab63f-recipe-b6f3681f-5b30-4aef-8462-008c2831c483-large_crop_1200.jpg",
            recipeLink = "https://t.me/obedkakdoma/148",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Банановый торт без выпечки",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/3c/46/57842/tort-iz-pechenya-smetany-i-bananov-bez-vypechki_1629264597_23_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/149",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Клубничное мороженое",
            type = "Десерты",
            imageUrl = "https://i.obozrevatel.com/food/recipemain/2019/1/22/9b560f81183e73fee4eef8eceb64a2d4klubnichnoyemorozhenoyedomadepositphot.jpg",
            recipeLink = "https://t.me/obedkakdoma/150",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Лимонный чизкейк",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/TD4qWectVmY/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/119",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Банановый чизкейк без выпечки",
            type = "Десерты",
            imageUrl = "https://i.pinimg.com/736x/01/ff/e9/01ffe9f18cfbf858616c659033130833.jpg",
            recipeLink = "https://t.me/obedkakdoma/118",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Суфле з 2х ингредиентов",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/ZKfn3B2G_cE/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/116",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Шоколадный десерт №2",
            type = "Десерты",
            imageUrl = "https://img-global.cpcdn.com/recipes/702df41a5b659c1c/680x482cq70/molochno-shokoladnyi-diesiert-%D0%BE%D1%81%D0%BD%D0%BE%D0%B2%D0%BD%D0%B5-%D1%84%D0%BE%D1%82%D0%BE-%D1%80%D0%B5%D1%86%D0%B5%D0%BF%D1%82%D0%B0.jpg",
            recipeLink = "https://t.me/obedkakdoma/114",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Печенье с джемом",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/3f/89/24204/pesochnoe-pechene-s-djemom_1617541906_11_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/99",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Банановые панкейки",
            type = "Завтрак",
            imageUrl = "https://static.1000.menu/img/content-v2/2b/4a/76165/bananovye-pankeiki-pp_1687677141_2_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/98",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Печенье с джемом №2",
            type = "Десерты",
            imageUrl = "https://kamelena.com/uploads/recipes/400/433/f433-pesochnoe-pechene-s-djemom.jpg",
            recipeLink = "https://t.me/obedkakdoma/97",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Ленивый Наполеон",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/ia9JuLoSjjc/hq720.jpg",
            recipeLink = "https://t.me/obedkakdoma/110",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Клубничный тирамису",
            type = "Десерты",
            imageUrl = "https://golden-flamingo.com.ua/wp-content/uploads/2023/05/strawberry-tiramisu-ft-recipe0321-4148336bab854769a1a4af51cf39358f-1.jpg",
            recipeLink = "https://t.me/obedkakdoma/109",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Нежные творожники",
            type = "Десерты",
            imageUrl = "https://img-global.cpcdn.com/recipes/b04c6882f65b396b/680x482cq70/samyie-niezhnyie-tvorozhniki-v-rassypchatoi-korzinkie-%D0%BE%D1%81%D0%BD%D0%BE%D0%B2%D0%BD%D0%B5-%D1%84%D0%BE%D1%82%D0%BE-%D1%80%D0%B5%D1%86%D0%B5%D0%BF%D1%82%D0%B0.jpg",
            recipeLink = "https://t.me/obedkakdoma/108",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Суфле №2",
            type = "Десерты",
            imageUrl = "https://irecommend.ru/sites/default/files/product-images/343613/dbKS3IoX6BAjm0GcdvNMYg.jpg",
            recipeLink = "https://t.me/obedkakdoma/106",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Банановый чизкейк №2",
            type = "Десерты",
            imageUrl = "https://www.rbc.ua/static/img/f/r/freepik__com__32__1_1200x675.jpg",
            recipeLink = "https://t.me/obedkakdoma/103",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Шоколадное суфле",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content/12444/shokoladnoe-sufle_1422383159_0_max.JPG",
            recipeLink = "https://t.me/obedkakdoma/100",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Торт «Муравейник»",
            type = "Десерты",
            imageUrl = "https://i.obozrevatel.com/food/recipemain/2019/2/25/asp.jpg",
            recipeLink = "https://t.me/obedkakdoma/96",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Пирог Milka",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/msGBbDcS3eQ/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/95",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Новогодние капкейки",
            type = "Десерты",
            imageUrl = "https://vsedeserti.ru/wp-content/uploads/2021/12/1-Novogodnie-kapkejki-na-2022-god-12-2.jpg",
            recipeLink = "https://t.me/obedkakdoma/94",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Творожная запеканка",
            type = "Десерты",
            imageUrl = "https://klopotenko.com/wp-content/uploads/2022/06/tvorozhnaya-zapekanka_siteweb-1000x600.jpg",
            recipeLink = "https://t.me/obedkakdoma/93",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Творожные трубочки к чаю",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/a17WKgW3RkI/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/92",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Имбирные пряники №3",
            type = "Десерты",
            imageUrl = "https://apostrophe.ua/uploads/image/7c422092d662194b3eb17c761633a6b7.jpg",
            recipeLink = "https://t.me/obedkakdoma/91",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Трайфл «Черный принц»",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/1kDtbglLecY/hq720.jpg",
            recipeLink = "https://t.me/obedkakdoma/90",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Торт «Птичье молоко»",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/d9/08/37893/tort-ptiche-moloko-po-gostu_1672412899_3_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/89",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Маковый рулет с творогом",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/D3oa0LOe1qg/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/88",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Мандариновые мини-чизкейки без выпечки",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/sNwSmXvdFAY/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/87",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Птичье молоко",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/Gz_OuR8P49g/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/86",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Слаткие конверты",
            type = "Десерты",
            imageUrl = "https://smachnota.com.ua/images/flt/blinchiki-sladkie/1040-800-bliny-sladkie.jpg",
            recipeLink = "https://t.me/obedkakdoma/85",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Творожные сырки в шоколаде №2",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/BYo9bkY75G0/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/84",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Нежный меренговый рулет",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/hXyrcRbvnTc/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/83",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Кексы с кремом и карамелью",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/nub05HqXbGA/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/82",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Шоколадный рулет с фруктами",
            type = "Десерты",
            imageUrl = "https://www.rbc.ua/static/img/5/6/5678_6_1300x820.jpg",
            recipeLink = "https://t.me/obedkakdoma/81",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Шоколадный рулет с фруктами",
            type = "Десерты",
            imageUrl = "https://www.rbc.ua/static/img/5/6/5678_6_1300x820.jpg",
            recipeLink = "https://t.me/obedkakdoma/81",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Очень ленивый Наполеон №2",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/S3bwPf3lrUU/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/80",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Классический чизкейк Нью-Йорк №2",
            type = "Десерты",
            imageUrl = "https://yasensvit.ua/uploads/recipes/prev/639ca77703b3a.jpg",
            recipeLink = "https://t.me/obedkakdoma/79",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Медовик",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/zn4bB7udHXY/sddefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/78",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Печенье Баунти",
            type = "Десерты",
            imageUrl = "https://realist.online/img/article/1148/68_main-v1643724687.jpg",
            recipeLink = "https://t.me/obedkakdoma/77",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Нежный рулет с фруктами №2",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/92/4f/19044/rulet-s-yablokami-v-duxovke_1679818737_14_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/76",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Творожные сладкие булочки",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content/35776/tvorojnye-bulochki-kak-pux-za-5-minut_1560363989_10_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/75",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "ПП Ватрушки",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/91/6b/87012/pp-vatrushki-s-tvorogom_1719038791_0_yb71z3s_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/74",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Творожные пончика",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content/25032/tvorojnye-ponchiki-klassicheskie_1516399344_1_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/73",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Кексы с рикоттой и малиной",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content/10992/keksj-s-malinoi-i-fundukom_1379011880_0_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/72",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Шоколадный кекс с чизкейком",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/AI8zu_bTnuw/hq720.jpg",
            recipeLink = "https://t.me/obedkakdoma/71",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Бананово-кокосовые кексы",
            type = "Десерты",
            imageUrl = "https://ifs.cook-time.com/preview/img1010/1010583.jpg",
            recipeLink = "https://t.me/obedkakdoma/70",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Турецкий кекс",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/6e/bc/69579/tureckii-shokoladnyi-keks-mokryi_1668892294_15_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/69",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Турецкий кекс",
            type = "Десерты",
            imageUrl = "https://ifs.cook-time.com/preview/img1010/1010583.jpg",
            recipeLink = "https://t.me/obedkakdoma/69",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Воздушные японские панкейки",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/36/ec/37561/yaponskie-pankeiki-pyshnye-vozdushnye-na-skovorode_1616599043_16_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/67",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Венский сырник",
            type = "Десерты",
            imageUrl = "https://dvazajci.com/wp-content/uploads/2020/12/7.jpg",
            recipeLink = "https://t.me/obedkakdoma/66",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Клубничное тирамису №2",
            type = "Десерты",
            imageUrl = "https://shuba.life/static/content/thumbs/1905x884/6/95/v3i3mc---c1905x884x50px50p-up--90caa610d34278bfb82120dc0af77956.jpg",
            recipeLink = "https://t.me/obedkakdoma/65",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Творожная запеканка №3",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content/35128/tvorojnaya-zapekanka-kak-v-detstve_1558702907_1_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/64",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Итальянский десерт крем-кофе",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/ddjvT19Wu84/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/63",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Тирамису №3",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/3gp50W4Rlm4/maxresdefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/61",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Заварной пирожное",
            type = "Десерты",
            imageUrl = "https://i.ytimg.com/vi/9GZLEY1lKCU/hq720.jpg",
            recipeLink = "https://t.me/obedkakdoma/60",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Клубничная каша",
            type = "Завтрак",
            imageUrl = "https://i.ytimg.com/vi/Fd27OU6z2wc/sddefault.jpg",
            recipeLink = "https://t.me/obedkakdoma/59",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Кофейное желе",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content/34313/molochno-kofeinoe-jele_1555635977_1_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/58",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Вкусные оладьи",
            type = "Десерты",
            imageUrl = "https://static.1000.menu/img/content-v2/d3/e8/24736/oladi-ochen-pyshnye-na-kefire_1594324969_14_max.jpg",
            recipeLink = "https://t.me/obedkakdoma/57",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Тирамису №4",
            type = "Десерты",
            imageUrl = "https://i0.wp.com/foxychef.ru/wp-content/uploads/2018/09/MG_52941.jpg",
            recipeLink = "https://t.me/obedkakdoma/56",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Моти",
            type = "Десерты",
            imageUrl = "https://bottva.ru/wp-content/uploads/2021/12/230114_daifuku_0.jpg",
            recipeLink = "https://t.me/obedkakdoma/55",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "ПП тирамису с бананом",
            type = "Десерты",
            imageUrl = "https://receptor.top/media/zoo/images/21211-1_dba2ff13f87cf7d0e694f5ff95f24b6e.jpg",
            recipeLink = "https://t.me/obedkakdoma/54",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "Сырники",
            type = "Десерты",
            imageUrl = "https://klopotenko.com/wp-content/uploads/2020/06/syrniki_klassicheskie_siteweb-1000x600.jpg",
            recipeLink = "https://t.me/obedkakdoma/53",
            isFavorite = false
        ),
        Dish(
            id = idNumber++,
            name = "7 Лучших коктейлей из cоулсов",
            type = "Напитки",
            imageUrl = "https://static.stratege.ru/trophies/NPWR07897_00/TROP016.PNG",
            recipeLink = "https://t.me/obedkakdoma/151",
            isFavorite = false
        ),
    )
