// makeSong page

interface lyrics{
    id: number,
    lyricsName: string
}

interface genre{
    id:number,
    genrename: string
}

interface createMusicForm{
    title : string,
    genre : string,
    intro : string,
    verse1: string,
    verse2: string,
    chorus: string,
    outro : string,
    bridge: string,
}

// interface LyricsList{
//     lyricsList: lyrics[]
// }