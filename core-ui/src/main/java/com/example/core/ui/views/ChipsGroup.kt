package com.example.core.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.JetTheme
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ChipGroup(
    chips: List<Chip>,
    chipClickedListener: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        FlowRow(
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 8.dp,
        ) {
            chips.forEach { chip ->
                JetChip(
                    chip = chip,
                    onChipClicked = chipClickedListener,
                )
            }
        }
    }
}

@Preview
@Composable
fun OctopusChipGroupDemoCompose() {
    JetTheme {
        Column(
            verticalArrangement = Arrangement
                .spacedBy(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                    ),
                verticalArrangement = Arrangement
                    .spacedBy(
                        space = 16.dp,
                    ),
            ) {
                var chips by remember {
                    mutableStateOf(
                        listOf(
                            Chip(
                                "Best Match",
                            ),
                            Chip(
                                "Rating",
                            ),
                            Chip(
                                "Order",
                            ),
                            Chip(
                                "Disabled Chip",
                            ),
                            Chip(
                                "Preselected Chip",
                                checked = true,
                            ),
                            Chip(
                                "Preselected disabled Chip",
                            ),
                        ),
                    )
                }

                val chipListener = { id: Int ->
                    val selectedIndex = chips.indexOfFirst { it.id == id }
                    val selectedChip = chips[selectedIndex]
                    chips = chips.toMutableList().map {
                        it.copy(checked = false)
                    }.toMutableList().apply {
                        set(
                            selectedIndex,
                            selectedChip.copy(checked = !selectedChip.checked),
                        )
                    }
                }

                ChipGroup(
                    chips = chips,
                    chipClickedListener = chipListener,
                )
            }
        }
    }
}