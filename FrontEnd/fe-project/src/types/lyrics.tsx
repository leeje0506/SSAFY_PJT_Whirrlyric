// makeSong page

interface lyrics {
  id: number;
  lyricsName: string;
}

interface genre {
  id: number;
  genrename: string;
}

interface createMusicForm {
  title: string;
  intro: string;
  verse1: string;
  verse2: string;
  chorus: string;
  bridge: string;
  outro: string;
  genre: string;
}

// interface LyricsList{
//     lyricsList: lyrics[]
// }
