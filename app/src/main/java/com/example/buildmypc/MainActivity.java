package com.example.buildmypc;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.buildmypc.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;

import static android.util.Log.d;
import static androidx.navigation.Navigation.findNavController;
import static androidx.navigation.ui.AppBarConfiguration.Builder;
import static androidx.navigation.ui.NavigationUI.navigateUp;
import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;
import static com.example.buildmypc.R.id.nav_gallery;
import static com.example.buildmypc.R.id.nav_home;
import static com.example.buildmypc.R.id.nav_host_fragment_content_main;
import static com.example.buildmypc.R.id.nav_newsfeed;
import static com.example.buildmypc.R.menu.main;
import static com.example.buildmypc.databinding.ActivityMainBinding.inflate;
import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;

public class MainActivity extends AppCompatActivity {
	private AppBarConfiguration mAppBarConfiguration;
	public static final AtomicReference<JSONObject> parts = new AtomicReference<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityMainBinding binding = inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		setSupportActionBar(binding.appBarMain.toolbar);
		binding.appBarMain.fab.setOnClickListener(view -> make(view, "Replace with your own action", LENGTH_LONG).setAction("Action", null).show());
//		Passing each menu ID as a set of IDs because each menu should be considered as top-level destinations.
		mAppBarConfiguration = new Builder(nav_home, nav_gallery, nav_newsfeed).setOpenableLayout(binding.drawerLayout).build();
		NavController navController = findNavController(this, nav_host_fragment_content_main);
		setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		setupWithNavController(binding.navView, navController);
		try {
			parts.set(new JSONObject("{\"cpu\":[{\"boost-clock-ghz\":4.2,\"cooler\":true,\"core-clock-ghz\":3.6,\"core-count\":6,\"core-family\":\"Matisse\",\"ecc\":false,\"integrated-gpu\":null,\"manufacturer\":\"AMD\",\"max-memory-support-gb\":128,\"microarchitecture\":\"Zen 2\",\"model\":\"Ryzen 5 3600\",\"series\":\"AMD Ryzen 5\",\"smt\":true,\"socket\":\"AM4\",\"tdp-w\":65},{\"boost-clock-ghz\":4.4,\"cooler\":true,\"core-clock-ghz\":3.6,\"core-count\":8,\"core-family\":\"Matisse\",\"ecc\":false,\"integrated-gpu\":null,\"manufacturer\":\"AMD\",\"max-memory-support-gb\":128,\"microarchitecture\":\"Zen 2\",\"model\":\"Ryzen 7 3700X\",\"series\":\"AMD Ryzen 7\",\"smt\":true,\"socket\":\"AM4\",\"tdp-w\":65},{\"boost-clock-ghz\":4.6,\"cooler\":true,\"core-clock-ghz\":3.7,\"core-count\":6,\"core-family\":\"Vermeer\",\"ecc\":false,\"integrated-gpu\":null,\"manufacturer\":\"AMD\",\"max-memory-support-gb\":128,\"microarchitecture\":\"Zen 3\",\"model\":\"Ryzen 5 5600X\",\"series\":\"AMD Ryzen 5\",\"smt\":true,\"socket\":\"AM4\",\"tdp-w\":65},{\"boost-clock-ghz\":4.7,\"cooler\":true,\"core-clock-ghz\":3.8,\"core-count\":8,\"core-family\":\"Vermeer\",\"ecc\":false,\"integrated-gpu\":null,\"manufacturer\":\"AMD\",\"max-memory-support-gb\":128,\"microarchitecture\":\"Zen 2\",\"model\":\"Ryzen 7 5800X\",\"series\":\"AMD Ryzen 5\",\"smt\":true,\"socket\":\"AM4\",\"tdp-w\":105},{\"boost-clock-ghz\":5.1,\"cooler\":true,\"core-clock-ghz\":3.8,\"core-count\":8,\"core-family\":\"Comet Lake\",\"ecc\":false,\"integrated-gpu\":\"Intel UHD Graphics 630\",\"manufacturer\":\"Intel\",\"max-memory-support-gb\":128,\"microarchitecture\":\"Comet Lake\",\"model\":\"Core i7-10700K\",\"series\":\"Intel Core i7\",\"smt\":true,\"socket\":\"LGA1200\",\"tdp-w\":125}],\"cooler\":[{\"fan-height-mm\":159,\"fan-rpm\":\"600 - 2000\",\"fanless\":false,\"manufacturer\":\"Cooler Master\",\"model\":\"Hyper 212 EVO\",\"noise-level-db\":\"9 - 36\",\"socket-support\":[\"AM2\",\"AM2+\",\"AM3\",\"AM3+\",\"AM4\",\"FM1\",\"FM2\",\"FM2+\",\"LGA1150\",\"LGA1151\",\"LGA1155\",\"LGA1156\",\"LGA1200\",\"LGA1366\",\"LGA2011\",\"LGA2011-3\",\"LGA2066\",\"LGA775\"],\"water-cooled\":false,\"water-cooler-size-mm\":null},{\"fan-height-mm\":159,\"fan-rpm\":\"650 - 2000\",\"fanless\":false,\"manufacturer\":\"Cooler Master\",\"model\":\"Hyper 212 RGB Black Edition\",\"noise-level-db\":\"8 - 30\",\"socket-support\":[\"AM2\",\"AM2+\",\"AM3\",\"AM3+\",\"AM4\",\"FM1\",\"FM2\",\"FM2+\",\"LGA1150\",\"LGA1151\",\"LGA1155\",\"LGA1156\",\"LGA1200\",\"LGA1366\",\"LGA2011\",\"LGA2011-3\",\"LGA2066\"],\"water-cooled\":false,\"water-cooler-size-mm\":null},{\"fan-height-mm\":null,\"fan-rpm\":\"650 - 1800\",\"fanless\":false,\"manufacturer\":\"Cooler Master\",\"model\":\"MASTERLIQUID ML240L RGB V2\",\"noise-level-db\":\"6 - 27\",\"socket-support\":[\"AM2\",\"AM2+\",\"AM3\",\"AM3+\",\"AM4\",\"FM1\",\"FM2\",\"FM2+\",\"LGA1150\",\"LGA1151\",\"LGA1155\",\"LGA1156\",\"LGA1200\",\"LGA1366\",\"LGA2011\",\"LGA2011-3\",\"LGA2066\"],\"water-cooled\":true,\"water-cooler-size-mm\":240},{\"fan-height-mm\":null,\"fan-rpm\":\"500 - 2000\",\"fanless\":false,\"manufacturer\":\"NZXT\",\"model\":\"Kraken X53\",\"noise-level-db\":\"21 - 36\",\"socket-support\":[\"AM4\",\"LGA1150\",\"LGA1151\",\"LGA1155\",\"LGA1156\",\"LGA1200\",\"LGA1366\",\"LGA2011\",\"LGA2011-3\",\"LGA2066\",\"sTR4\",\"sTRX4\"],\"water-cooled\":true,\"water-cooler-size-mm\":240},{\"fan-height-mm\":null,\"fan-rpm\":\"500 - 1800\",\"fanless\":false,\"manufacturer\":\"NZXT\",\"model\":\"Kraken Z73\",\"noise-level-db\":\"21 - 36\",\"socket-support\":[\"AM4\",\"LGA1150\",\"LGA1151\",\"LGA1155\",\"LGA1156\",\"LGA1200\",\"LGA1366\",\"LGA2011\",\"LGA2011-3\",\"LGA2066\",\"sTR4\",\"sTRX4\"],\"water-cooled\":true,\"water-cooler-size-mm\":240}],\"motherboard\":[{\"chipset\":\"AMD B450\",\"ecc\":false,\"form-factor\":\"ATX\",\"m.2-slots\":[\"2242/2260/2280/22110 M-key\"],\"manufacturer\":\"MSI\",\"max-memory-gb\":64,\"memory-slots\":4,\"memory-speeds\":[\"DDR4-1866\",\"DDR4-2133\",\"DDR4-2400\",\"DDR4-2666\",\"DDR4-2800\",\"DDR4-2933\",\"DDR4-3000\",\"DDR4-3200\",\"DDR4-3466\",\"DDR4-3600\",\"DDR4-3733\",\"DDR4-3866\",\"DDR4-4000\",\"DDR4-4133\"],\"memory-type\":\"DDR4\",\"model\":\"B450 TOMAHAWK MAX\",\"msata-slots\":0,\"onboard-ethernet\":[\"1 x 1 Gbit/s\"],\"onboard-video\":\"CPU-Dependent\",\"pci-slots\":{\"e-x1\":3,\"e-x16\":2,\"e-x4\":0,\"e-x8\":0,\"standard\":0},\"raid\":false,\"sata-6gbps\":6,\"sli-crossfire\":\"CrossFire Capable\",\"socket\":\"AM4\",\"usb-headers\":{\"2.0\":2,\"3.2\":{\"gen-1\":1,\"gen-2\":0,\"gen-2x2\":0}},\"wireless\":null},{\"chipset\":\"AMD X570\",\"ecc\":false,\"form-factor\":\"ATX\",\"m.2-slots\":[\"2242/2260/2280/22110 M-key\",\"2242/2260/2280/22110 M-key\"],\"manufacturer\":\"Asus\",\"max-memory-gb\":128,\"memory-slots\":4,\"memory-speeds\":[\"DDR4-2133\",\"DDR4-2400\",\"DDR4-2666\",\"DDR4-2800\",\"DDR4-3000\",\"DDR4-3200\",\"DDR4-3300\",\"DDR4-3400\",\"DDR4-3466\",\"DDR4-3600\",\"DDR4-3866\",\"DDR4-4000\",\"DDR4-4133\",\"DDR4-4266\",\"DDR4-4400\"],\"memory-type\":\"DDR4\",\"model\":\"TUF GAMING X570-PLUS (WI-FI)\",\"msata-slots\":0,\"onboard-ethernet\":[\"1 x 1 Gbit/s\"],\"onboard-video\":\"CPU-Dependent\",\"pci-slots\":{\"e-x1\":2,\"e-x16\":2,\"e-x4\":0,\"e-x8\":0,\"standard\":0},\"raid\":true,\"sata-6gbps\":8,\"sli-crossfire\":\"CrossFire Capable\",\"socket\":\"AM4\",\"usb-headers\":{\"2.0\":2,\"3.2\":{\"gen-1\":1,\"gen-2\":0,\"gen-2x2\":0}},\"wireless\":\"Wi-Fi 5\"},{\"chipset\":\"AMD B550\",\"ecc\":false,\"form-factor\":\"ATX\",\"m.2-slots\":[\"2242/2260/2280/22110 M-key\",\"2242/2260/2280/22110 M-key\"],\"manufacturer\":\"Asus\",\"max-memory-gb\":128,\"memory-slots\":4,\"memory-speeds\":[\"DDR4-2133\",\"DDR4-2400\",\"DDR4-2666\",\"DDR4-2800\",\"DDR4-3000\",\"DDR4-3200\",\"DDR4-3466\",\"DDR4-3600\",\"DDR4-3866\",\"DDR4-4000\",\"DDR4-4133\",\"DDR4-4400\"],\"memory-type\":\"DDR4\",\"model\":\"STRIX B550-F GAMING (WI-FI)\",\"msata-slots\":0,\"onboard-ethernet\":[\"1 x 2.5 Gbit/s\"],\"onboard-video\":\"CPU-Dependent\",\"pci-slots\":{\"e-x1\":3,\"e-x16\":2,\"e-x4\":0,\"e-x8\":0,\"standard\":0},\"raid\":true,\"sata-6gbps\":6,\"sli-crossfire\":\"CrossFire Capable\",\"socket\":\"AM4\",\"usb-headers\":{\"2.0\":2,\"3.2\":{\"gen-1\":1,\"gen-2\":0,\"gen-2x2\":0}},\"wireless\":\"Wi-Fi 6\"},{\"chipset\":\"Intel Z490\",\"ecc\":false,\"form-factor\":\"ATX\",\"m.2-slots\":[\"2242/2260/2280/22110 M-key\",\"2242/2260/2280 M-key\",\"2230 E-key\"],\"manufacturer\":\"MSI\",\"max-memory-gb\":128,\"memory-slots\":4,\"memory-speeds\":[\"DDR4-2133\",\"DDR4-2400\",\"DDR4-2666\",\"DDR4-2933\",\"DDR4-3000\",\"DDR4-3200\",\"DDR4-3300\",\"DDR4-3333\",\"DDR4-3400\",\"DDR4-3466\",\"DDR4-3600\",\"DDR4-3733\",\"DDR4-3866\",\"DDR4-4000\",\"DDR4-4133\",\"DDR4-4200\",\"DDR4-4266\",\"DDR4-4300\",\"DDR4-4400\",\"DDR4-4533\",\"DDR4-4600\",\"DDR4-4800\"],\"memory-type\":\"DDR4\",\"model\":\"Z490-A PRO\",\"msata-slots\":0,\"onboard-ethernet\":[\"1 x 2.5 Gbit/s\"],\"onboard-video\":\"CPU-Dependent\",\"pci-slots\":{\"e-x1\":3,\"e-x16\":2,\"e-x4\":0,\"e-x8\":0,\"standard\":0},\"raid\":true,\"sata-6gbps\":6,\"sli-crossfire\":\"CrossFire Capable\",\"socket\":\"LGA1200\",\"usb-headers\":{\"2.0\":2,\"3.2\":{\"gen-1\":2,\"gen-2\":1,\"gen-2x2\":0}},\"wireless\":null},{\"chipset\":\"AMD B550\",\"ecc\":false,\"form-factor\":\"ATX\",\"m.2-slots\":[\"2242/2260/2280/22110 M-key\",\"2242/2260/2280 M-key\"],\"manufacturer\":\"MSI\",\"max-memory-gb\":128,\"memory-slots\":4,\"memory-speeds\":[\"DDR4-1866\",\"DDR4-2133\",\"DDR4-2400\",\"DDR4-2666\",\"DDR4-2800\",\"DDR4-2933\",\"DDR4-3000\",\"DDR4-3200\",\"DDR4-3466\",\"DDR4-3600\",\"DDR4-3733\",\"DDR4-3866\",\"DDR4-4000\",\"DDR4-4133\",\"DDR4-4266\",\"DDR4-4400\",\"DDR4-4533\",\"DDR4-4600\",\"DDR4-4800\",\"DDR4-4866\"],\"memory-type\":\"DDR4\",\"model\":\"MAG B550 TOMAHAWK\",\"msata-slots\":0,\"onboard-ethernet\":[\"1 x 1 Gbit/s\",\"1 x 2.5 Gbit/s\"],\"onboard-video\":\"CPU-Dependent\",\"pci-slots\":{\"e-x1\":2,\"e-x16\":2,\"e-x4\":0,\"e-x8\":0,\"standard\":0},\"raid\":true,\"sata-6gbps\":6,\"sli-crossfire\":\"CrossFire Capable\",\"socket\":\"AM4\",\"usb-headers\":{\"2.0\":2,\"3.2\":{\"gen-1\":1,\"gen-2\":1,\"gen-2x2\":0}},\"wireless\":null}],\"memory\":[{\"cas-latency\":16,\"ddr-gen\":4,\"ecc\":false,\"first-word-latency-ns\":10,\"form-factor\":\"288-pin DIMM\",\"heat-spreader\":true,\"manufacturer\":\"Corsair\",\"model\":\"Vengeance LPX 16 GB\",\"modules\":{\"quantity\":2,\"size-gb\":8},\"price-per-gb-usd\":6.437,\"speed-mhz\":3200,\"timing\":\"16-18-18-36\",\"voltage\":1.35},{\"cas-latency\":16,\"ddr-gen\":4,\"ecc\":false,\"first-word-latency-ns\":10,\"form-factor\":\"288-pin DIMM\",\"heat-spreader\":true,\"manufacturer\":\"Corsair\",\"model\":\"Vengeance RGB Pro 16 GB\",\"modules\":{\"quantity\":2,\"size-gb\":8},\"price-per-gb-usd\":6.562,\"speed-mhz\":3200,\"timing\":\"16-18-18-36\",\"voltage\":1.35},{\"cas-latency\":16,\"ddr-gen\":4,\"ecc\":false,\"first-word-latency-ns\":10,\"form-factor\":\"288-pin DIMM\",\"heat-spreader\":true,\"manufacturer\":\"Crucial\",\"model\":\"Ballistix 16 GB\",\"modules\":{\"quantity\":2,\"size-gb\":8},\"price-per-gb-usd\":5.374,\"speed-mhz\":3200,\"timing\":null,\"voltage\":1.35},{\"cas-latency\":16,\"ddr-gen\":4,\"ecc\":false,\"first-word-latency-ns\":10,\"form-factor\":\"288-pin DIMM\",\"heat-spreader\":true,\"manufacturer\":\"Corsair\",\"model\":\"Vengeance RGB Pro 32 GB\",\"modules\":{\"quantity\":2,\"size-gb\":16},\"price-per-gb-usd\":7.093,\"speed-mhz\":3200,\"timing\":\"16-18-18-36\",\"voltage\":1.35},{\"cas-latency\":18,\"ddr-gen\":4,\"ecc\":false,\"first-word-latency-ns\":10,\"form-factor\":\"288-pin DIMM\",\"heat-spreader\":true,\"manufacturer\":\"G.Skill\",\"model\":\"Vengeance RGB Pro 32 GB\",\"modules\":{\"quantity\":2,\"size-gb\":8},\"price-per-gb-usd\":6.874,\"speed-mhz\":3600,\"timing\":\"18-22-22-42\",\"voltage\":1.35}],\"storage\":[{\"cache-mb\":256,\"capacity\":{\"size\":2,\"unit\":\"TB\"},\"form-factor\":3.5,\"interface\":\"SATA 6 Gb/s\",\"manufacturer\":\"Seagate\",\"model\":\"Barracuda Compute 2 TB\",\"nvme\":false,\"price-per-gb-usd\":0.028,\"rpm\":7200,\"type\":\"HDD\"},{\"cache-mb\":1024,\"capacity\":{\"size\":1,\"unit\":\"TB\"},\"form-factor\":2.5,\"interface\":\"SATA 6 Gb/s\",\"manufacturer\":\"Samsung\",\"model\":\"860 Evo 1 TB\",\"nvme\":false,\"price-per-gb-usd\":0.110,\"rpm\":null,\"type\":\"SSD\"},{\"cache-mb\":null,\"capacity\":{\"size\":1,\"unit\":\"TB\"},\"form-factor\":\"M.2-2280\",\"interface\":\"M.2\",\"manufacturer\":\"Western Digital\",\"model\":\"Blue SN550 1 TB\",\"nvme\":true,\"price-per-gb-usd\":0.110,\"rpm\":null,\"type\":\"SSD\"},{\"cache-mb\":512,\"capacity\":{\"size\":500,\"unit\":\"GB\"},\"form-factor\":\"M.2-2280\",\"interface\":\"M.2\",\"manufacturer\":\"Samsung\",\"model\":\"970 Evo 500 GB\",\"nvme\":true,\"price-per-gb-usd\":0.240,\"rpm\":null,\"type\":\"HDD\"},{\"cache-mb\":1024,\"capacity\":{\"size\":1,\"unit\":\"TB\"},\"form-factor\":\"M.2-2280\",\"interface\":\"M.2\",\"manufacturer\":\"Samsung\",\"model\":\"980 Pro 1 TB\",\"nvme\":true,\"price-per-gb-usd\":0.210,\"rpm\":null,\"type\":\"SSD\"}],\"gpu\":[{\"boost-clock-mhz\":1730,\"chipset\":\"GeForce RTX 3070\",\"cooling\":\"2 Fans\",\"core-clock-mhz\":1500,\"effective-memory-clock-mhz\":16000,\"expansion-slot-width\":2,\"external-power\":\"1 PCIe 12-pin\",\"frame-sync\":\"G-Sync\",\"interface\":\"PCIe x16\",\"length-mm\":242,\"manufacturer\":\"NVIDIA\",\"memory-gb\":8,\"memory-type\":\"GDDR6\",\"model\":\"GeForce RTX 3070 Founders Edition\",\"tdp-w\":220,\"video-ports\":{\"dp\":3,\"dvi\":0,\"hdmi\":1,\"mini-dp\":0,\"mini-hdmi\":0}},{\"boost-clock-mhz\":1882,\"chipset\":\"GeForce RTX 3060\",\"cooling\":\"2 Fans\",\"core-clock-mhz\":1320,\"effective-memory-clock-mhz\":15000,\"expansion-slot-width\":2,\"external-power\":\"1 PCIe 8-pin\",\"frame-sync\":\"G-Sync\",\"interface\":\"PCIe x16\",\"length-mm\":201.8,\"manufacturer\":\"EVGA\",\"memory-gb\":12,\"memory-type\":\"GDDR6\",\"model\":\"GeForce RTX 3060 XC\",\"tdp-w\":170,\"video-ports\":{\"dp\":3,\"dvi\":0,\"hdmi\":1,\"mini-dp\":0,\"mini-hdmi\":0}},{\"boost-clock-mhz\":1710,\"chipset\":\"GeForce RTX 3080\",\"cooling\":\"2 Fans\",\"core-clock-mhz\":1440,\"effective-memory-clock-mhz\":19000,\"expansion-slot-width\":2,\"external-power\":\"1 PCIe 12-pin\",\"frame-sync\":\"G-Sync\",\"interface\":\"PCIe x16\",\"length-mm\":285,\"manufacturer\":\"NVIDIA\",\"memory-gb\":10,\"memory-type\":\"GDDR6\",\"model\":\"GeForce RTX 3080 Founders Edition\",\"tdp-w\":320,\"video-ports\":{\"dp\":3,\"dvi\":0,\"hdmi\":1,\"mini-dp\":0,\"mini-hdmi\":0}},{\"boost-clock-mhz\":1845,\"chipset\":\"GeForce RTX 3070\",\"cooling\":\"3 Fans\",\"core-clock-mhz\":1500,\"effective-memory-clock-mhz\":14000,\"expansion-slot-width\":2,\"external-power\":\"2 PCIe 8-pin\",\"frame-sync\":\"G-Sync\",\"interface\":\"PCIe x16\",\"length-mm\":299.9,\"manufacturer\":\"Asus\",\"memory-gb\":8,\"memory-type\":\"GDDR6\",\"model\":\"TUF-RTX3070-O8G-GAMING\",\"tdp-w\":220,\"video-ports\":{\"dp\":3,\"dvi\":0,\"hdmi\":2,\"mini-dp\":0,\"mini-hdmi\":0}},{\"boost-clock-mhz\":1392,\"chipset\":\"GeForce RTX 1050 Ti\",\"cooling\":\"3 Fans\",\"core-clock-mhz\":1290,\"effective-memory-clock-mhz\":7008,\"expansion-slot-width\":2,\"external-power\":\"2 PCIe 8-pin\",\"frame-sync\":\"G-Sync\",\"interface\":\"PCIe x16\",\"length-mm\":192,\"manufacturer\":\"Asus\",\"memory-gb\":4,\"memory-type\":\"GDDR5\",\"model\":\"PH-GTX1050TI-4G\",\"tdp-w\":75,\"video-ports\":{\"dp\":1,\"dvi\":1,\"hdmi\":1,\"mini-dp\":0,\"mini-hdmi\":0}}],\"case\":[{\"color\":\"White\",\"dimensions\":[\"428 mm x 210 mm x 460 mm\",\"16.85\\\" x 8.268\\\" x 18.11\\\"\"],\"expansion-slots\":{\"full-height\":7,\"half-height\":0},\"front-panel-usb\":[\"USB 3.2 Gen 2 Type-C\",\"USB 3.2 Gen 1 Type-A\"],\"internal-drive-bays\":{\"2.5\":2,\"3.5\":2},\"manufacturer\":\"NZXT\",\"max-gpu-length\":[\"381 mm / 15\\\" With Drive Cages\",\"381 mm / 15\\\" Without Drive Cages\"],\"mb-form-factor\":[\"ATX\",\"Micro ATX\",\"Mini ITX\"],\"model\":\"H510\",\"psu\":null,\"psu-shroud\":true,\"side-panel\":\"Tempered Glass\",\"type\":\"ATX Mid Tower\",\"volume\":[\"41.345 L\",\"1.46 ft³\"]},{\"color\":\"Black\",\"dimensions\":[\"428 mm x 210 mm x 460 mm\",\"16.85\\\" x 8.268\\\" x 18.11\\\"\"],\"expansion-slots\":{\"full-height\":7,\"half-height\":0},\"front-panel-usb\":[\"USB 3.2 Gen 2 Type-C\",\"USB 3.2 Gen 1 Type-A\"],\"internal-drive-bays\":{\"2.5\":2,\"3.5\":2},\"manufacturer\":\"NZXT\",\"max-gpu-length\":[\"381 mm / 15\\\" With Drive Cages\",\"381 mm / 15\\\" Without Drive Cages\"],\"mb-form-factor\":[\"ATX\",\"Micro ATX\",\"Mini ITX\"],\"model\":\"H510\",\"psu\":null,\"psu-shroud\":true,\"side-panel\":\"Tempered Glass\",\"type\":\"ATX Mid Tower\",\"volume\":[\"41.345 L\",\"1.46 ft³\"]},{\"color\":\"Black\",\"dimensions\":[\"453 mm x 230 mm x 466 mm\",\"17.835\\\" x 9.055\\\" x 18.346\\\"\"],\"expansion-slots\":{\"full-height\":7,\"half-height\":0},\"front-panel-usb\":[\"USB 3.2 Gen 2 Type-C\",\"USB 3.2 Gen 1 Type-A\"],\"internal-drive-bays\":{\"2.5\":2,\"3.5\":2},\"manufacturer\":\"Corsair\",\"max-gpu-length\":[\"360 mm / 14.173\\\"\"],\"mb-form-factor\":[\"ATX\",\"Micro ATX\",\"Mini ITX\"],\"model\":\"4000D Airflow\",\"psu\":null,\"psu-shroud\":true,\"side-panel\":\"Tinted Tempered Glass\",\"type\":\"ATX Mid Tower\",\"volume\":[\"48.553 L\",\"1.715 ft³\"]},{\"color\":\"Black\",\"dimensions\":[\"457 mm x 216 mm x 455 mm\",\"17.992\\\" x 8.504\\\" x 17.913\\\"\"],\"expansion-slots\":{\"full-height\":7,\"half-height\":0},\"front-panel-usb\":[\"USB 3.2 Gen 1 Type-A\"],\"internal-drive-bays\":{\"2.5\":4,\"3.5\":2},\"manufacturer\":\"Corsair\",\"max-gpu-length\":[\"370 mm / 14.567\\\"\"],\"mb-form-factor\":[\"ATX\",\"Micro ATX\",\"Mini ITX\"],\"model\":\"275R Airflow\",\"psu\":null,\"psu-shroud\":true,\"side-panel\":\"Tempered Glass\",\"type\":\"ATX Mid Tower\",\"volume\":[\"44.914 L\",\"1.586 ft³\"]},{\"color\":\"Black\",\"dimensions\":[\"445 mm x 272 mm x 446 mm\",\"17.52\\\" x 10.709\\\" x 17.559\\\"\"],\"expansion-slots\":{\"full-height\":8,\"half-height\":0},\"front-panel-usb\":[\"USB 3.2 Gen 2 Type-C\",\"USB 3.2 Gen 1 Type-A\"],\"internal-drive-bays\":{\"2.5\":4,\"3.5\":2},\"manufacturer\":\"Lian Li\",\"max-gpu-length\":[\"420 mm / 16.535\\\"\"],\"mb-form-factor\":[\"ATX\",\"EATX\",\"Micro ATX\",\"Mini ITX\"],\"model\":\"PC-O11DX\",\"psu\":null,\"psu-shroud\":true,\"side-panel\":\"Tempered Glass\",\"type\":\"ATX Full Tower\",\"volume\":[\"53.984 L\",\"1.906 ft³\"]}],\"psu\":[{\"connectors\":{\"eps\":2,\"molex-4-pin\":4,\"pcie-6+2-pin\":6,\"sata\":10},\"efficiency-rating\":\"80+ Gold\",\"fanless\":false,\"form-factor\":\"ATX\",\"length-mm\":160,\"manufacturer\":\"Corsair\",\"model\":\"RM750 (2019)\",\"modular\":\"Full\",\"wattage\":750},{\"connectors\":{\"eps\":1,\"molex-4-pin\":3,\"pcie-6+2-pin\":2,\"sata\":6},\"efficiency-rating\":\"80+ Bronze\",\"fanless\":false,\"form-factor\":\"ATX\",\"length-mm\":140,\"manufacturer\":\"EVGA\",\"model\":\"600 BQ\",\"modular\":\"Semi\",\"wattage\":600},{\"connectors\":{\"eps\":2,\"molex-4-pin\":4,\"pcie-6+2-pin\":6,\"sata\":12},\"efficiency-rating\":\"80+ Gold\",\"fanless\":false,\"form-factor\":\"ATX\",\"length-mm\":160,\"manufacturer\":\"Corsair\",\"model\":\"RM850 (2019)\",\"modular\":\"Full\",\"wattage\":850},{\"connectors\":{\"eps\":1,\"molex-4-pin\":4,\"pcie-6+2-pin\":6,\"sata\":9},\"efficiency-rating\":\"80+ Gold\",\"fanless\":false,\"form-factor\":\"ATX\",\"length-mm\":150,\"manufacturer\":\"EVGA\",\"model\":\"SuperNOVA 650 GA\",\"modular\":\"Full\",\"wattage\":650},{\"connectors\":{\"eps\":1,\"molex-4-pin\":4,\"pcie-6+2-pin\":2,\"sata\":6},\"efficiency-rating\":\"80+ Bronze\",\"fanless\":false,\"form-factor\":\"ATX\",\"length-mm\":140,\"manufacturer\":\"Corsair\",\"model\":\"CX650M\",\"modular\":\"Semi\",\"wattage\":650}],\"os\":[{\"bit-mode\":64,\"manufacturer\":\"Microsoft\",\"max-memory-support-gb\":128,\"type\":\"Windows\",\"version\":{\"edition\":\"Windows 10 Home\",\"oem-retail\":\"oem\"}},{\"bit-mode\":64,\"manufacturer\":\"Microsoft\",\"max-memory-support-gb\":2048,\"type\":\"Windows\",\"version\":{\"edition\":\"Windows 10 Pro\",\"oem-retail\":\"oem\"}},{\"bit-mode\":\"dual\",\"manufacturer\":\"Microsoft\",\"max-memory-support-gb\":2048,\"type\":\"Windows\",\"version\":{\"edition\":\"Windows 10 Pro\",\"oem-retail\":\"retail\"}},{\"bit-mode\":\"dual\",\"manufacturer\":\"Microsoft\",\"max-memory-support-gb\":192,\"type\":\"Windows\",\"version\":{\"edition\":\"Windows 7 Professional\",\"oem-retail\":\"retail\"}},{\"bit-mode\":\"dual\",\"manufacturer\":\"Microsoft\",\"max-memory-support-gb\":2048,\"type\":\"Windows\",\"version\":{\"edition\":\"Windows 10 Pro\",\"oem-retail\":\"retail\"}}],\"monitor\":[{\"aspect-ratio\":\"16:9\",\"brightness-cd-per-sq-m\":1000,\"curved-screen\":false,\"frame-sync\":[\"G-Sync Ultimate\"],\"hdr-tier\":\"HDR 1000\",\"inbuilt-speakers\":true,\"interface\":[{\"name\":\"HDMI\",\"quantity\":4},{\"name\":\"DisplayPort\",\"quantity\":1}],\"manufacturer\":\"Asus\",\"model\":\"ROG Swift PG65UQ\",\"panel-type\":\"VA\",\"refresh-rate-hz\":144,\"resolution\":\"3840 x 2160\",\"response-time-ms\":4,\"screen-size-in\":64.5,\"viewing-angle\":\"178° H x 178° V\",\"widescreen\":true},{\"aspect-ratio\":\"16:9\",\"brightness-cd-per-sq-m\":250,\"curved-screen\":false,\"frame-sync\":[\"FreeSync\"],\"hdr-tier\":null,\"inbuilt-speakers\":false,\"interface\":[{\"name\":\"HDMI\",\"quantity\":2},{\"name\":\"DisplayPort\",\"quantity\":1}],\"manufacturer\":\"MSI\",\"model\":\"Optix G241\",\"panel-type\":\"IPS\",\"refresh-rate-hz\":144,\"resolution\":\"1920 x 1080\",\"response-time-ms\":1,\"screen-size-in\":23.8,\"viewing-angle\":\"178° H x 178° V\",\"widescreen\":true},{\"aspect-ratio\":\"16:9\",\"brightness-cd-per-sq-m\":250,\"curved-screen\":true,\"frame-sync\":[\"FreeSync\"],\"hdr-tier\":null,\"inbuilt-speakers\":false,\"interface\":[{\"name\":\"HDMI\",\"quantity\":2},{\"name\":\"VGA\",\"quantity\":1},{\"name\":\"DisplayPort\",\"quantity\":1}],\"manufacturer\":\"AOC\",\"model\":\"C241G\",\"panel-type\":\"VA\",\"refresh-rate-hz\":144,\"resolution\":\"1920 x 1080\",\"response-time-ms\":1,\"screen-size-in\":24,\"viewing-angle\":\"178° H x 178° V\",\"widescreen\":true},{\"aspect-ratio\":\"16:9\",\"brightness-cd-per-sq-m\":400,\"curved-screen\":false,\"frame-sync\":[\"G-Sync\"],\"hdr-tier\":null,\"inbuilt-speakers\":false,\"interface\":[{\"name\":\"HDMI\",\"quantity\":1},{\"name\":\"DisplayPort\",\"quantity\":1}],\"manufacturer\":\"Asus\",\"model\":\"ROG Swift PG259QN\",\"panel-type\":\"IPS\",\"refresh-rate-hz\":360,\"resolution\":\"1920 x 1080\",\"response-time-ms\":1,\"screen-size-in\":24.5,\"viewing-angle\":\"178° H x 178° V\",\"widescreen\":true},{\"aspect-ratio\":\"16:9\",\"brightness-cd-per-sq-m\":350,\"curved-screen\":false,\"frame-sync\":[\"FreeSync\",\"G-Sync Compatible\"],\"hdr-tier\":null,\"inbuilt-speakers\":true,\"interface\":[{\"name\":\"HDMI\",\"quantity\":1},{\"name\":\"DVI-D Dual-Link\",\"quantity\":1},{\"name\":\"DisplayPort\",\"quantity\":1}],\"manufacturer\":\"Asus\",\"model\":\"VG248QG\",\"panel-type\":\"TN\",\"refresh-rate-hz\":165,\"resolution\":\"1920 x 1080\",\"response-time-ms\":0.5,\"screen-size-in\":24,\"viewing-angle\":\"170° H x 160° V\",\"widescreen\":true}]}"));
			d("OOG", "works");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(main, menu);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp() {
		return navigateUp(findNavController(this, nav_host_fragment_content_main), mAppBarConfiguration) || super.onSupportNavigateUp();
	}
}