 // hello gemini ye comment tumhare liye is pure design ko exact copy karna Bina Kisi uvai change kiye kotlin app banana hai pura is design ko acche se copy karna aur acche se read karna ek bhi galti mat karna
import { useState, useEffect, useCallback, useRef } from "react";
 
 i
const C = {
  bg: "#07071A", card: "#0F0F28", cardL: "#161635",
  purple: "#8B3FD9", purpleD: "#6020A8", purpleL: "#B060FF",
  gold: "#F5A623", goldL: "#FFD166", goldD: "#C8841C",
  green: "#00E676", red: "#FF4560", blue: "#00B4FF",
  white: "#FFFFFF", grey: "#5A6070", greyL: "#9CA3AF",
};

const px = (n) => `${n}px`;

// ─── REUSABLE UI ────────────────────────────────────────────────────────────

const Btn = ({ children, onClick, variant = "primary", style = {} }) => {
  const base = {
    border: "none", borderRadius: px(14), padding: "13px 20px",
    fontWeight: 800, fontSize: px(14), cursor: "pointer",
    fontFamily: "inherit", transition: "all 0.15s", width: "100%", ...style
  };
  const variants = {
    primary: { background: `liner-gradient(135deg, ${C.purple}, ${C.purpleL})`, color: C.white, boxShadow: `0 6px 20px ${C.purple}55` },
    gold: { background: `linear-gradient(135deg, ${C.gold}, ${C.goldL})`, color: C.bg, boxShadow: `0 6px 20px ${C.gold}44` },
    ghost: { background: "rgba(255,255,255,0.04)", color: C.greyL, border: "1px solid rgba(255,255,255,0.08)" },
    danger: { background: `${C.red}22`, color: C.red, border: `1px solid ${C.red}44` },
  };
  return <button onClick={onClick} style={{ ...base, ...variants[variant] }}>{children}</button>;
};

const Card = ({ children, glow, style = {} }) => (
  <div style={{
    background: `linear-gradient(145deg, ${C.cardL}, ${C.card})`,
    borderRadius: px(20), padding: px(16),
    border: `1px solid ${glow === "gold" ? C.gold + "33" : glow === "green" ? C.green + "33" : "rgba(139,63,217,0.2)"}`,
    boxShadow: glow === "gold" ? `0 4px 30px ${C.gold}12` : glow === "green" ? `0 4px 30px ${C.green}12` : `0 4px 20px rgba(0,0,0,0.3)`,
    ...style
  }}>{children}</div>
);

const Tag = ({ children, color = C.purple }) => (
  <span style={{ background: color + "22", color, border: `1px solid ${color}44`, borderRadius: px(20), padding: "3px 10px", fontSize: px(11), fontWeight: 700 }}>{children}</span>
);

const ProgressBar = ({ value, max, color = C.gold }) => (
  <div style={{ height: px(5), background: "rgba(255,255,255,0.07)", borderRadius: px(5) }}>
    <div style={{ width: `${(value / max) * 100}%`, height: "100%", background: `linear-gradient(90deg, ${color}, ${color}AA)`, borderRadius: px(5), transition: "width 0.4s ease" }} />
  </div>
);

// ─── BOTTOM NAV ─────────────────────────────────────────────────────────────

const NAV = [
  { id: "home", icon: "🏠", label: "Home" },
  { id: "tasks", icon: "⚡", label: "Tasks" },
  { id: "games", icon: "🎮", label: "Games" },
  { id: "profile", icon: "👤", label: "Me" },
];

const BottomNav = ({ active, go }) => (
  <div style={{
    position: "absolute", bottom: 0, left: 0, right: 0,
    background: "rgba(7,7,26,0.92)", backdropFilter: "blur(20px)",
    borderTop: "1px solid rgba(139,63,217,0.18)",
    display: "flex", padding: "10px 0 18px"
  }}>
    {NAV.map(t => (
      <button key={t.id} onClick={() => go(t.id)} style={{
        flex: 1, background: "none", border: "none", cursor: "pointer",
        display: "flex", flexDirection: "column", alignItems: "center", gap: px(3)
      }}>
        <div style={{ fontSize: px(22), transition: "all 0.2s", transform: active === t.id ? "scale(1.2)" : "scale(1)", filter: active === t.id ? `drop-shadow(0 0 8px ${C.gold})` : "none" }}>{t.icon}</div>
        <span style={{ fontSize: px(10), fontWeight: 700, color: active === t.id ? C.gold : C.grey }}>{t.label}</span>
        {active === t.id && <div style={{ width: px(4), height: px(4), borderRadius: "50%", background: C.gold }} />}
      </button>
    ))}
  </div>
);

// ─── SCREENS ────────────────────────────────────────────────────────────────

function HomeScreen({ go }) {
  return (
    <div style={{ padding: "16px 16px 90px", overflowY: "auto", height: "100%" }}>
      {/* Header */}
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: px(18) }}>
        <div>
          <div style={{ fontSize: px(12), color: C.grey }}>Welcome back,</div>
          <div style={{ fontSize: px(22), fontWeight: 900, color: C.white }}>Rahul 👋</div>
        </div>
        <div style={{ width: px(44), height: px(44), borderRadius: "50%", background: `linear-gradient(135deg, ${C.purple}, ${C.purpleL})`, display: "flex", alignItems: "center", justifyContent: "center", fontSize: px(20), position: "relative", cursor: "pointer" }}>
          🔔
          <div style={{ position: "absolute", top: -2, right: -2, width: px(16), height: px(16), background: C.red, borderRadius: "50%", fontSize: px(9), fontWeight: 800, color: "white", display: "flex", alignItems: "center", justifyContent: "center", border: `2px solid ${C.bg}` }}>3</div>
        </div>
      </div>

      {/* Hero Balance Card */}
      <div style={{
        background: `linear-gradient(135deg, #2A0870 0%, #0D0D30 60%, #1A0A3A 100%)`,
        borderRadius: px(26), padding: "22px 22px 18px",
        border: `1px solid ${C.purple}55`,
        boxShadow: `0 12px 40px rgba(139,63,217,0.3), inset 0 1px 0 rgba(255,255,255,0.08)`,
        marginBottom: px(14), position: "relative", overflow: "hidden"
      }}>
        <div style={{ position: "absolute", top: -40, right: -40, width: px(160), height: px(160), borderRadius: "50%", background: `radial-gradient(circle, ${C.gold}15, transparent 70%)` }} />
        <div style={{ position: "absolute", bottom: -20, left: -20, width: px(100), height: px(100), borderRadius: "50%", background: `radial-gradient(circle, ${C.purple}20, transparent 70%)` }} />
        <div style={{ fontSize: px(11), color: "rgba(255,255,255,0.5)", letterSpacing: 2, textTransform: "uppercase", marginBottom: px(6) }}>Total Balance</div>
        <div style={{ fontSize: px(38), fontWeight: 900, color: C.gold, letterSpacing: -2, marginBottom: px(10) }}>⚡ 1,700 <span style={{ fontSize: px(16), fontWeight: 600, opacity: 0.8 }}>Sparks</span></div>
        <div style={{ display: "flex", gap: px(8), marginBottom: px(14), flexWrap: "wrap" }}>
          <Tag color={C.gold}>🔥 Day 7 Streak</Tag>
          <Tag color={C.greyL}>🥉 Rookie</Tag>
        </div>
        <div style={{ fontSize: px(11), color: "rgba(255,255,255,0.4)", marginBottom: px(6) }}>Rising Creator mein 910 RP aur</div>
        <ProgressBar value={90} max={1000} />
      </div>

      {/* Daily Task Banner */}
      <div onClick={() => go("tasks")} style={{
        background: `linear-gradient(135deg, #1A0832, #0F0F28)`,
        border: `1px solid ${C.gold}44`, borderRadius: px(20), padding: "16px 18px",
        marginBottom: px(14), cursor: "pointer",
        boxShadow: `0 0 24px ${C.gold}15`
      }}>
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <div>
            <div style={{ display: "flex", alignItems: "center", gap: px(8), marginBottom: px(6) }}>
              <span style={{ fontSize: px(16) }}>⚡</span>
              <span style={{ fontSize: px(14), fontWeight: 800, color: C.white }}>AAJKA TASK</span>
              <span style={{ background: `${C.red}22`, color: C.red, border: `1px solid ${C.red}44`, borderRadius: px(6), padding: "1px 7px", fontSize: px(10), fontWeight: 700 }}>LIVE</span>
            </div>
            <div style={{ fontSize: px(12), color: C.grey }}>5 tasks • ⚡ 400 Sparks • ⏰ 08:23:10</div>
          </div>
          <div style={{ background: `linear-gradient(135deg, ${C.gold}, ${C.goldL})`, borderRadius: px(12), padding: "10px 14px", fontSize: px(13), fontWeight: 800, color: C.bg }}>Go →</div>
        </div>
      </div>

      {/* Quick Actions */}
      <div style={{ fontSize: px(11), fontWeight: 700, color: C.grey, letterSpacing: 2, marginBottom: px(10) }}>QUICK ACTIONS</div>
      <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: px(10), marginBottom: px(16) }}>
        {[
          { icon: "🎮", title: "Games", sub: "Bonus Sparks!", action: () => go("games"), glow: "purple" },
          { icon: "🎰", title: "Spin Wheel", sub: "1 Free Spin!", action: () => go("spin"), glow: "gold" },
          { icon: "🎁", title: "Daily Bonus", sub: "Claim Now!", action: () => {}, glow: "gold" },
          { icon: "🏆", title: "Leaderboard", sub: "Rank #23", action: () => {}, glow: "purple" },
        ].map((a, i) => (
          <Card key={i} glow={a.glow} style={{ cursor: "pointer", padding: "14px" }} >
            <div onClick={a.action} style={{ display: "flex", flexDirection: "column", gap: px(4) }}>
              <div style={{ fontSize: px(26) }}>{a.icon}</div>
              <div style={{ fontSize: px(13), fontWeight: 800, color: C.white }}>{a.title}</div>
              <div style={{ fontSize: px(11), color: a.glow === "gold" ? C.gold : C.purple, fontWeight: 600 }}>{a.sub}</div>
            </div>
          </Card>
        ))}
      </div>

      {/* Recent Activity */}
      <div style={{ fontSize: px(11), fontWeight: 700, color: C.grey, letterSpacing: 2, marginBottom: px(10) }}>RECENT ACTIVITY</div>
      <Card>
        {[
          { icon: "✅", text: "Task Complete", detail: "App Install", amount: "+400", pos: true },
          { icon: "🎰", text: "Mystery Box", detail: "Daily Free", amount: "+175", pos: true },
          { icon: "📦", text: "Views Delivered", detail: "1,000 views", amount: "✓", pos: true },
        ].map((a, i) => (
          <div key={i} style={{ display: "flex", alignItems: "center", justifyContent: "space-between", padding: "11px 0", borderBottom: i < 2 ? "1px solid rgba(255,255,255,0.04)" : "none" }}>
            <div style={{ display: "flex", alignItems: "center", gap: px(12) }}>
              <div style={{ width: px(36), height: px(36), borderRadius: px(10), background: `${C.gold}15`, display: "flex", alignItems: "center", justifyContent: "center", fontSize: px(16) }}>{a.icon}</div>
              <div>
                <div style={{ fontSize: px(13), color: C.white, fontWeight: 600 }}>{a.text}</div>
                <div style={{ fontSize: px(11), color: C.grey }}>{a.detail}</div>
              </div>
            </div>
            <span style={{ color: C.gold, fontWeight: 800, fontSize: px(14) }}>{a.amount.startsWith("+") ? `⚡${a.amount}` : a.amount}</span>
          </div>
        ))}
      </Card>
    </div>
  );
}

function TasksScreen({ go }) {
  const [sel, setSel] = useState(null);
  const tasks = [
    { icon: "📱", title: "App Install", desc: "\"FunBuzz\" install karo + register karo", time: "~3 min", type: "app_install" },
    { icon: "📋", title: "Quick Survey", desc: "3 sawal ka jawab do", time: "~2 min", type: "survey" },
    { icon: "🌐", title: "Free Registration", desc: "Website pe free account banao", time: "~4 min", type: "register" },
    { icon: "🎬", title: "Watch & Answer", desc: "Short video + 2 questions", time: "~3 min", type: "video" },
    { icon: "📝", title: "Form Fill", desc: "Basic form fill karo", time: "~2 min", type: "form" },
  ];
  return (
    <div style={{ padding: "16px 16px 90px", overflowY: "auto", height: "100%" }}>
      <div style={{ fontSize: px(20), fontWeight: 900, color: C.white, marginBottom: px(4) }}>⚡ Aajka Task</div>
      <div style={{ background: `${C.gold}15`, border: `1px solid ${C.gold}30`, borderRadius: px(12), padding: "10px 14px", marginBottom: px(18), display: "flex", alignItems: "center", gap: px(8) }}>
        <span>🎯</span>
        <span style={{ fontSize: px(12), color: C.gold, fontWeight: 700 }}>Koi bhi 1 task → ⚡ 400 Sparks guaranteed!</span>
      </div>
      <div style={{ display: "flex", flexDirection: "column", gap: px(10) }}>
        {tasks.map((t, i) => (
          <div key={i} onClick={() => setSel(sel === i ? null : i)} style={{
            background: sel === i ? `linear-gradient(135deg, ${C.purple}30, ${C.card})` : `linear-gradient(145deg, ${C.cardL}, ${C.card})`,
            borderRadius: px(18), padding: "16px",
            border: `1px solid ${sel === i ? C.purple : "rgba(255,255,255,0.05)"}`,
            boxShadow: sel === i ? `0 0 24px ${C.purple}25` : "none",
            cursor: "pointer", transition: "all 0.2s"
          }}>
            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "flex-start" }}>
              <div style={{ display: "flex", gap: px(12), flex: 1 }}>
                <div style={{ width: px(44), height: px(44), borderRadius: px(12), background: `${C.purple}25`, display: "flex", alignItems: "center", justifyContent: "center", fontSize: px(22), flexShrink: 0 }}>{t.icon}</div>
                <div>
                  <div style={{ fontSize: px(14), fontWeight: 800, color: C.white, marginBottom: px(3) }}>{i + 1}. {t.title}</div>
                  <div style={{ fontSize: px(12), color: C.grey, marginBottom: px(8) }}>{t.desc}</div>
                  <div style={{ display: "flex", gap: px(8) }}>
                    <Tag color={C.greyL}>⏱️ {t.time}</Tag>
                    <Tag color={C.gold}>⚡ 400</Tag>
                  </div>
                </div>
              </div>
              <div style={{ width: px(22), height: px(22), borderRadius: "50%", border: `2px solid ${sel === i ? C.purple : C.grey}`, background: sel === i ? C.purple : "transparent", display: "flex", alignItems: "center", justifyContent: "center", fontSize: px(12), flexShrink: 0 }}>
                {sel === i ? "✓" : ""}
              </div>
            </div>
            {sel === i && (
              <div style={{ marginTop: px(14) }}>
                <Btn onClick={() => go("task_success")} variant="primary">▶️ Start Karo — ⚡ 400 Sparks Kamao</Btn>
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}

function GamesScreen({ go }) {
  const games = [
    { icon: "🧠", title: "Quiz Master", sub: "GK + Bollywood", reward: "+30 Sparks", color: C.blue, id: "quiz" },
    { icon: "🎯", title: "Tap Frenzy", sub: "30 sec challenge", reward: "+20 Sparks", color: C.green, id: "tap" },
    { icon: "🎰", title: "Spin Wheel", sub: "Daily free spin!", reward: "Up to +500 Sparks", color: C.gold, id: "spin" },
    { icon: "🃏", title: "Card Match", sub: "Memory game", reward: "+40 Sparks", color: C.purple, id: "cards" },
    { icon: "🔢", title: "Math Rush", sub: "Quick calculations", reward: "+25 Sparks", color: "#FF6B9D", id: "math" },
    { icon: "🔤", title: "Word Hunt", sub: "Find hidden words", reward: "+35 Sparks", color: "#00CEC9", id: "words" },
  ];
  return (
    <div style={{ padding: "16px 16px 90px", overflowY: "auto", height: "100%" }}>
      <div style={{ fontSize: px(20), fontWeight: 900, color: C.white, marginBottom: px(4) }}>🎮 Games</div>
      <div style={{ fontSize: px(12), color: C.grey, marginBottom: px(18) }}>Task ke baad bonus Sparks kamao!</div>
      <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: px(12) }}>
        {games.map((g, i) => (
          <div key={i} onClick={() => go(g.id)} style={{
            background: `linear-gradient(145deg, ${g.color}18, ${C.card})`,
            border: `1px solid ${g.color}33`,
            borderRadius: px(20), padding: "18px 14px",
            cursor: "pointer", transition: "all 0.2s",
            boxShadow: `0 4px 20px ${g.color}12`
          }}>
            <div style={{ fontSize: px(34), marginBottom: px(10), filter: `drop-shadow(0 0 8px ${g.color}80)` }}>{g.icon}</div>
            <div style={{ fontSize: px(14), fontWeight: 800, color: C.white, marginBottom: px(3) }}>{g.title}</div>
            <div style={{ fontSize: px(11), color: C.grey, marginBottom: px(10) }}>{g.sub}</div>
            <div style={{ background: `${g.color}22`, border: `1px solid ${g.color}44`, borderRadius: px(8), padding: "4px 10px", fontSize: px(11), fontWeight: 700, color: g.color, display: "inline-block" }}>{g.reward}</div>
          </div>
        ))}
      </div>
    </div>
  );
}

function QuizGame({ go }) {
  const qs = [
    { q: "India ki capital kya hai?", opts: ["Mumbai", "New Delhi", "Chennai", "Kolkata"], ans: 1 },
    { q: "Bharat ka rashtriya khel kaun sa hai?", opts: ["Cricket", "Football", "Hockey", "Kabaddi"], ans: 2 },
    { q: "Taj Mahal kahan hai?", opts: ["Delhi", "Jaipur", "Agra", "Lucknow"], ans: 2 },
    { q: "1 + 1 × 0 + 1 = ?", opts: ["0", "1", "2", "3"], ans: 2 },
    { q: "Instagram kis company ka hai?", opts: ["Google", "Twitter", "Meta", "Apple"], ans: 2 },
  ];
  const [cur, setCur] = useState(0);
  const [selected, setSelected] = useState(null);
  const [score, setScore] = useState(0);
  const [done, setDone] = useState(false);
  const [time, setTime] = useState(15);

  useEffect(() => {
    if (done || selected !== null) return;
    const t = setTimeout(() => {
      if (time > 0) setTime(t => t - 1);
      else nextQ();
    }, 1000);
    return () => clearTimeout(t);
  }, [time, done, selected]);

  const pick = (i) => {
    if (selected !== null) return;
    setSelected(i);
    if (i === qs[cur].ans) setScore(s => s + 1);
    setTimeout(() => nextQ(), 800);
  };

  const nextQ = () => {
    if (cur + 1 >= qs.length) { setDone(true); return; }
    setCur(c => c + 1); setSelected(null); setTime(15);
  };

  if (done) return (
    <div style={{ padding: "40px 24px 90px", display: "flex", flexDirection: "column", alignItems: "center", textAlign: "center" }}>
      <div style={{ fontSize: px(60), marginBottom: px(12) }}>🧠</div>
      <div style={{ fontSize: px(22), fontWeight: 900, color: C.white, marginBottom: px(6) }}>Quiz Complete!</div>
      <div style={{ fontSize: px(14), color: C.grey, marginBottom: px(24) }}>{score}/5 sahi jawab</div>
      <Card glow="gold" style={{ padding: "24px 40px", marginBottom: px(24), textAlign: "center" }}>
        <div style={{ fontSize: px(40), fontWeight: 900, color: C.gold }}>+{score * 6}</div>
        <div style={{ fontSize: px(14), color: C.gold, fontWeight: 700 }}>⚡ Sparks Earned!</div>
      </Card>
      <Btn onClick={() => go("games")} variant="primary">← Games Pe Wapas</Btn>
    </div>
  );

  const q = qs[cur];
  return (
    <div style={{ padding: "20px 16px 90px", height: "100%", display: "flex", flexDirection: "column" }}>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: px(20) }}>
        <button onClick={() => go("games")} style={{ background: "none", border: "none", color: C.greyL, fontSize: px(14), cursor: "pointer" }}>← Back</button>
        <Tag color={C.blue}>🧠 Quiz Master</Tag>
        <Tag color={time < 6 ? C.red : C.greyL}>⏱️ {time}s</Tag>
      </div>
      <div style={{ marginBottom: px(20) }}>
        <ProgressBar value={cur} max={qs.length} color={C.blue} />
        <div style={{ fontSize: px(11), color: C.grey, textAlign: "right", marginTop: px(4) }}>{cur + 1} / {qs.length}</div>
      </div>
      <Card style={{ marginBottom: px(24), padding: "22px" }}>
        <div style={{ fontSize: px(16), fontWeight: 700, color: C.white, lineHeight: 1.5 }}>{q.q}</div>
      </Card>
      <div style={{ display: "flex", flexDirection: "column", gap: px(10) }}>
        {q.opts.map((o, i) => {
          let bg = `${C.cardL}`, border = "rgba(255,255,255,0.08)", color = C.white;
          if (selected !== null) {
            if (i === q.ans) { bg = `${C.green}22`; border = C.green + "88"; color = C.green; }
            else if (i === selected && selected !== q.ans) { bg = `${C.red}22`; border = C.red + "88"; color = C.red; }
          }
          return (
            <div key={i} onClick={() => pick(i)} style={{ background: bg, border: `1.5px solid ${border}`, borderRadius: px(14), padding: "14px 18px", cursor: "pointer", transition: "all 0.2s" }}>
              <span style={{ color, fontWeight: 700, fontSize: px(14) }}>{String.fromCharCode(65 + i)}. {o}</span>
            </div>
          );
        })}
      </div>
    </div>
  );
}

function TapGame({ go }) {
  const [sparks, setSparks] = useState([]);
  const [score, setScore] = useState(0);
  const [timeLeft, setTimeLeft] = useState(30);
  const [started, setStarted] = useState(false);
  const [done, setDone] = useState(false);
  const areaRef = useRef(null);
  const idRef = useRef(0);

  useEffect(() => {
    if (!started || done) return;
    const t = setInterval(() => {
      setTimeLeft(tl => {
        if (tl <= 1) { setDone(true); clearInterval(t); return 0; }
        return tl - 1;
      });
    }, 1000);
    return () => clearInterval(t);
  }, [started, done]);

  useEffect(() => {
    if (!started || done) return;
    const t = setInterval(() => {
      const id = ++idRef.current;
      setSparks(s => [...s.slice(-10), { id, x: Math.random() * 75, y: Math.random() * 70, size: 28 + Math.random() * 20 }]);
    }, 700);
    return () => clearInterval(t);
  }, [started, done]);

  const tap = (id) => {
    setSparks(s => s.filter(x => x.id !== id));
    setScore(s => s + 1);
  };

  const reward = Math.min(score * 2, 20);

  if (done) return (
    <div style={{ padding: "40px 24px 90px", display: "flex", flexDirection: "column", alignItems: "center", textAlign: "center" }}>
      <div style={{ fontSize: px(60), marginBottom: px(12) }}>🎯</div>
      <div style={{ fontSize: px(22), fontWeight: 900, color: C.white, marginBottom: px(6) }}>Time Up!</div>
      <div style={{ fontSize: px(14), color: C.grey, marginBottom: px(24) }}>{score} sparks tap kiye!</div>
      <Card glow="green" style={{ padding: "24px 40px", marginBottom: px(24), textAlign: "center" }}>
        <div style={{ fontSize: px(40), fontWeight: 900, color: C.green }}>+{reward}</div>
        <div style={{ fontSize: px(14), color: C.green, fontWeight: 700 }}>⚡ Sparks Earned!</div>
      </Card>
      <Btn onClick={() => go("games")} variant="primary">← Games Pe Wapas</Btn>
    </div>
  );

  return (
    <div style={{ padding: "16px", height: "100%", display: "flex", flexDirection: "column" }}>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: px(14) }}>
        <button onClick={() => go("games")} style={{ background: "none", border: "none", color: C.greyL, fontSize: px(14), cursor: "pointer" }}>← Back</button>
        <Tag color={C.green}>🎯 Tap Frenzy</Tag>
        <Tag color={timeLeft < 10 ? C.red : C.greyL}>⏱️ {timeLeft}s</Tag>
      </div>
      <div style={{ display: "flex", justifyContent: "space-around", marginBottom: px(14) }}>
        <Card style={{ textAlign: "center", padding: "10px 24px" }}><div style={{ color: C.gold, fontSize: px(22), fontWeight: 900 }}>{score}</div><div style={{ color: C.grey, fontSize: px(11) }}>Tapped</div></Card>
        <Card style={{ textAlign: "center", padding: "10px 24px" }}><div style={{ color: C.green, fontSize: px(22), fontWeight: 900 }}>+{reward}</div><div style={{ color: C.grey, fontSize: px(11) }}>Sparks</div></Card>
      </div>
      {!started ? (
        <div style={{ flex: 1, display: "flex", alignItems: "center", justifyContent: "center" }}>
          <Btn onClick={() => setStarted(true)} variant="gold" style={{ width: px(180), fontSize: px(16) }}>🎯 Start Game!</Btn>
        </div>
      ) : (
        <div ref={areaRef} style={{ flex: 1, background: `${C.cardL}`, borderRadius: px(20), position: "relative", overflow: "hidden", border: `1px solid rgba(0,230,118,0.15)` }}>
          <div style={{ position: "absolute", inset: 0, display: "flex", alignItems: "center", justifyContent: "center", color: "rgba(255,255,255,0.04)", fontSize: px(80) }}>⚡</div>
          {sparks.map(s => (
            <div key={s.id} onClick={() => tap(s.id)} style={{
              position: "absolute", left: `${s.x}%`, top: `${s.y}%`,
              fontSize: px(s.size), cursor: "pointer", userSelect: "none",
              animation: "popIn 0.2s ease", transition: "transform 0.1s",
              filter: "drop-shadow(0 0 8px rgba(245,166,35,0.8))"
            }}>⚡</div>
          ))}
          <div style={{ position: "absolute", bottom: px(12), left: 0, right: 0, textAlign: "center", fontSize: px(13), color: "rgba(255,255,255,0.3)", fontWeight: 600 }}>TAP THE SPARKS!</div>
        </div>
      )}
    </div>
  );
}

function SpinWheel({ go }) {
  const [spinning, setSpinning] = useState(false);
  const [result, setResult] = useState(null);
  const [angle, setAngle] = useState(0);
  const segments = [
    { label: "30 Sparks", color: "#4ECDC4", sparks: 30 },
    { label: "75 Sparks", color: "#45B7D1", sparks: 75 },
    { label: "50 Sparks", color: "#96CEB4", sparks: 50 },
    { label: "500 Sparks!", color: "#F5A623", sparks: 500 },
    { label: "100 Sparks", color: "#DDA0DD", sparks: 100 },
    { label: "25 Sparks", color: "#98D8C8", sparks: 25 },
    { label: "200 Sparks", color: "#F7DC6F", sparks: 200 },
    { label: "150 Sparks", color: "#BB8FCE", sparks: 150 },
  ];
  const spin = () => {
    if (spinning || result) return;
    setSpinning(true);
    const extra = 1440 + Math.random() * 720;
    const finalAngle = angle + extra;
    setAngle(finalAngle);
    setTimeout(() => {
      const normalized = ((finalAngle % 360) + 360) % 360;
      const segIndex = Math.floor((360 - normalized) / (360 / segments.length)) % segments.length;
      setResult(segments[segIndex]);
      setSpinning(false);
    }, 3000);
  };

  const segAngle = 360 / segments.length;

  return (
    <div style={{ padding: "16px 16px 90px", display: "flex", flexDirection: "column", alignItems: "center", overflowY: "auto", height: "100%" }}>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", width: "100%", marginBottom: px(20) }}>
        <button onClick={() => go("games")} style={{ background: "none", border: "none", color: C.greyL, fontSize: px(14), cursor: "pointer" }}>← Back</button>
        <Tag color={C.gold}>🎰 Spin Wheel</Tag>
        <Tag color={C.greyL}>1 Free/Day</Tag>
      </div>

      {/* Pointer */}
      <div style={{ fontSize: px(28), marginBottom: px(-8), zIndex: 10, filter: `drop-shadow(0 2px 4px ${C.gold}80)` }}>▼</div>

      {/* Wheel */}
      <div style={{ position: "relative", width: px(280), height: px(280), marginBottom: px(24) }}>
        <svg width="280" height="280" viewBox="0 0 280 280" style={{ transition: spinning ? "transform 3s cubic-bezier(0.17,0.67,0.35,1)" : "none", transform: `rotate(${angle}deg)`, position: "absolute" }}>
          {segments.map((s, i) => {
            const startAngle = (i * segAngle - 90) * (Math.PI / 180);
            const endAngle = ((i + 1) * segAngle - 90) * (Math.PI / 180);
            const x1 = 140 + 130 * Math.cos(startAngle), y1 = 140 + 130 * Math.sin(startAngle);
            const x2 = 140 + 130 * Math.cos(endAngle), y2 = 140 + 130 * Math.sin(endAngle);
            const mid = (startAngle + endAngle) / 2;
            const tx = 140 + 88 * Math.cos(mid), ty = 140 + 88 * Math.sin(mid);
            return (
              <g key={i}>
                <path d={`M 140 140 L ${x1} ${y1} A 130 130 0 0 1 ${x2} ${y2} Z`} fill={s.color} stroke={C.bg} strokeWidth="2" />
                <text x={tx} y={ty} textAnchor="middle" dominantBaseline="middle" fill={C.bg} fontSize="9" fontWeight="800" fontFamily="Poppins, sans-serif" transform={`rotate(${i * segAngle + segAngle / 2}, ${tx}, ${ty})`}>{s.sparks}</text>
              </g>
            );
          })}
          <circle cx="140" cy="140" r="24" fill={C.bg} stroke={C.gold} strokeWidth="3" />
          <text x="140" y="144" textAnchor="middle" dominantBaseline="middle" fill={C.gold} fontSize="14">⚡</text>
        </svg>
        <div style={{ position: "absolute", inset: 0, borderRadius: "50%", border: `3px solid ${C.gold}66`, boxShadow: `0 0 30px ${C.gold}20` }} />
      </div>

      {result ? (
        <Card glow="gold" style={{ textAlign: "center", padding: "20px 40px", marginBottom: px(20) }}>
          <div style={{ fontSize: px(40), fontWeight: 900, color: C.gold }}>+{result.sparks}</div>
          <div style={{ color: C.gold, fontWeight: 700 }}>⚡ Sparks Won!</div>
        </Card>
      ) : (
        <div style={{ width: "100%", marginBottom: px(20) }}>
          <Btn onClick={spin} variant="gold" style={{ fontSize: px(16) }}>{spinning ? "Spinning... 🌀" : "🎰 SPIN!"}</Btn>
        </div>
      )}
      {result && <Btn onClick={() => go("games")} variant="primary">← Games Pe Wapas</Btn>}
    </div>
  );
}

function TaskSuccess({ go }) {
  return (
    <div style={{ padding: "40px 24px 90px", display: "flex", flexDirection: "column", alignItems: "center", textAlign: "center", overflowY: "auto", height: "100%" }}>
      <div style={{ fontSize: px(64), marginBottom: px(10) }}>🎉</div>
      <div style={{ fontSize: px(24), fontWeight: 900, color: C.white, marginBottom: px(4) }}>TASK COMPLETE!</div>
      <div style={{ fontSize: px(13), color: C.grey, marginBottom: px(28) }}>Bahut badhiya Rahul!</div>
      <div style={{
        background: `linear-gradient(135deg, #1A2A00, ${C.card})`,
        border: `2px solid ${C.gold}66`, borderRadius: px(26),
        padding: "28px 48px", marginBottom: px(20),
        boxShadow: `0 0 40px ${C.gold}20`
      }}>
        <div style={{ fontSize: px(48), fontWeight: 900, color: C.gold, letterSpacing: -2 }}>+400</div>
        <div style={{ fontSize: px(16), color: C.gold, fontWeight: 700 }}>⚡ Sparks Earned!</div>
        <div style={{ fontSize: px(12), color: C.grey, marginTop: px(6) }}>New Balance: ⚡ 2,100</div>
      </div>
      <div style={{ display: "flex", gap: px(20), marginBottom: px(24) }}>
        {[["⭐", "+40 RP"], ["🔥", "Day 7!"], ["🎰", "+3 Tickets"]].map(([ic, lb], i) => (
          <div key={i} style={{ textAlign: "center" }}>
            <div style={{ fontSize: px(24) }}>{ic}</div>
            <div style={{ fontSize: px(11), color: C.greyL, marginTop: px(4) }}>{lb}</div>
          </div>
        ))}
      </div>
      <div style={{ background: `${C.green}15`, border: `1px solid ${C.green}33`, borderRadius: px(12), padding: "10px 18px", marginBottom: px(20), fontSize: px(12), color: C.green, width: "100%" }}>
        ✅ Aaj ka task ho gaya! Kal naye 5 tasks aayenge.
      </div>
      <Btn onClick={() => go("games")} variant="primary" style={{ marginBottom: px(10) }}>🎮 Bonus Games Khelo</Btn>
      <Btn onClick={() => go("home")} variant="ghost">🏠 Home Pe Jaao</Btn>
    </div>
  );
}

function ProfileScreen() {
  const badges = [
    { icon: "🏅", title: "First Order", ok: true }, { icon: "🔥", title: "3-Day", ok: true },
    { icon: "🔥", title: "7-Day", ok: true }, { icon: "🤝", title: "First Ref", ok: false },
    { icon: "💎", title: "Elite", ok: false }, { icon: "👑", title: "VaultKing", ok: false },
  ];
  return (
    <div style={{ padding: "16px 16px 90px", overflowY: "auto", height: "100%" }}>
      <div style={{ fontSize: px(20), fontWeight: 900, color: C.white, marginBottom: px(20) }}>👤 My Vault</div>
      <div style={{ background: `linear-gradient(135deg, ${C.purple}44, ${C.card})`, border: `1px solid ${C.purple}44`, borderRadius: px(24), padding: "20px", marginBottom: px(16), display: "flex", alignItems: "center", gap: px(16) }}>
        <div style={{ width: px(64), height: px(64), borderRadius: "50%", background: `linear-gradient(135deg, ${C.purple}, ${C.purpleL})`, display: "flex", alignItems: "center", justifyContent: "center", fontSize: px(24), fontWeight: 900, color: "white", border: `3px solid ${C.gold}44`, flexShrink: 0 }}>RA</div>
        <div>
          <div style={{ fontSize: px(20), fontWeight: 900, color: C.white }}>Rahul</div>
          <div style={{ fontSize: px(12), color: C.grey, marginBottom: px(8) }}>#VLT-00001 • Member since May 2025</div>
          <Tag color={C.gold}>🥉 Rookie Vaulter</Tag>
        </div>
      </div>
      <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: px(10), marginBottom: px(16) }}>
        {[["🔥", "7 Days", "Streak"], ["👁️", "1,000", "Views"], ["⭐", "90", "RP"], ["🤝", "0", "Referrals"]].map(([ic, v, l], i) => (
          <Card key={i} style={{ textAlign: "center", padding: "16px" }}>
            <div style={{ fontSize: px(26), marginBottom: px(4) }}>{ic}</div>
            <div style={{ fontSize: px(22), fontWeight: 900, color: C.white }}>{v}</div>
            <div style={{ fontSize: px(11), color: C.grey }}>{l}</div>
          </Card>
        ))}
      </div>
      <div style={{ fontSize: px(11), fontWeight: 700, color: C.grey, letterSpacing: 2, marginBottom: px(10) }}>ACHIEVEMENTS</div>
      <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr 1fr", gap: px(8), marginBottom: px(16) }}>
        {badges.map((b, i) => (
          <div key={i} style={{ background: b.ok ? `${C.gold}12` : C.card, border: `1px solid ${b.ok ? C.gold + "44" : "rgba(255,255,255,0.05)"}`, borderRadius: px(14), padding: "14px 8px", textAlign: "center", opacity: b.ok ? 1 : 0.4 }}>
            <div style={{ fontSize: px(24), marginBottom: px(6), filter: b.ok ? "none" : "grayscale(1)" }}>{b.icon}</div>
            <div style={{ fontSize: px(10), color: b.ok ? C.gold : C.grey, fontWeight: 700 }}>{b.title}</div>
            {!b.ok && <div style={{ fontSize: px(12), marginTop: px(2) }}>🔒</div>}
          </div>
        ))}
      </div>
      <div style={{ display: "flex", flexDirection: "column", gap: px(8) }}>
        {[["📲", "Telegram Bot Pe Jaao"], ["⚙️", "Settings"]].map(([ic, tx], i) => (
          <button key={i} style={{ width: "100%", background: C.cardL, border: "1px solid rgba(255,255,255,0.06)", borderRadius: px(14), padding: "14px 18px", color: C.white, fontWeight: 600, fontSize: px(14), cursor: "pointer", textAlign: "left", display: "flex", alignItems: "center", gap: px(12), fontFamily: "inherit" }}>
            <span style={{ fontSize: px(18) }}>{ic}</span>{tx}<span style={{ marginLeft: "auto", color: C.grey }}>→</span>
          </button>
        ))}
      </div>
    </div>
  );
}

// ─── MAIN APP ────────────────────────────────────────────────────────────────

export default function App() {
  const [screen, setScreen] = useState("home");
  const navScreens = ["home", "tasks", "games", "profile"];

  const renderScreen = () => {
    switch (screen) {
      case "home": return <HomeScreen go={setScreen} />;
      case "tasks": return <TasksScreen go={setScreen} />;
      case "games": return <GamesScreen go={setScreen} />;
      case "quiz": return <QuizGame go={setScreen} />;
      case "tap": return <TapGame go={setScreen} />;
      case "spin": return <SpinWheel go={setScreen} />;
      case "task_success": return <TaskSuccess go={setScreen} />;
      case "profile": return <ProfileScreen />;
      default: return <HomeScreen go={setScreen} />;
    }
  };

  const activeNav = navScreens.includes(screen) ? screen : (screen === "task_success" ? "tasks" : screen.includes("quiz") || screen.includes("tap") || screen.includes("spin") || screen.includes("cards") ? "games" : "home");

  return (
    <div style={{ minHeight: "100vh", background: "radial-gradient(ellipse at top, #0D0720 0%, #050510 60%)", display: "flex", alignItems: "center", justifyContent: "center", fontFamily: "'Poppins', sans-serif", padding: "20px" }}>
      <style>{`
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800;900&display=swap');
        * { box-sizing: border-box; margin: 0; padding: 0; }
        ::-webkit-scrollbar { width: 0; }
        @keyframes popIn { from { transform: scale(0.5); opacity: 0; } to { transform: scale(1); opacity: 1; } }
      `}</style>
      <div style={{ display: "flex", flexDirection: "column", alignItems: "center", gap: px(20) }}>
        <div style={{ fontSize: px(12), color: "#444", fontFamily: "monospace", letterSpacing: 3, textTransform: "uppercase" }}>⚡ InstaVault — Interactive Preview</div>
        <div style={{ width: px(375), height: px(780), background: C.bg, borderRadius: px(52), overflow: "hidden", position: "relative", border: "8px solid #111", boxShadow: "0 0 0 2px #222, 0 40px 80px rgba(0,0,0,0.9), 0 0 80px rgba(139,63,217,0.2)" }}>
          {/* Status Bar */}
          <div style={{ height: px(44), background: C.bg, display: "flex", alignItems: "center", justifyContent: "space-between", padding: "0 24px", fontSize: px(12), color: C.grey, fontWeight: 700 }}>
            <span>9:41</span><div style={{ display: "flex", gap: px(6) }}><span>📶</span><span>🔋</span></div>
          </div>
          <div style={{ height: `calc(100% - ${px(44)})`, position: "relative", overflow: "hidden" }}>
            {renderScreen()}
            <BottomNav active={activeNav} go={setScreen} />
          </div>
        </div>
        {/* Screen Pills */}
        <div style={{ display: "flex", gap: px(8), flexWrap: "wrap", justifyContent: "center", maxWidth: px(420) }}>
          {[
            { id: "home", l: "🏠 Home" }, { id: "tasks", l: "⚡ Tasks" },
            { id: "games", l: "🎮 Games" }, { id: "quiz", l: "🧠 Quiz" },
            { id: "tap", l: "🎯 Tap Game" }, { id: "spin", l: "🎰 Spin" },
            { id: "task_success", l: "🎉 Success" }, { id: "profile", l: "👤 Profile" },
          ].map(s => (
            <button key={s.id} onClick={() => setScreen(s.id)} style={{ background: screen === s.id ? `linear-gradient(135deg, ${C.purple}, ${C.purpleL})` : "rgba(255,255,255,0.04)", border: `1px solid ${screen === s.id ? C.purple : "rgba(255,255,255,0.08)"}`, borderRadius: px(20), padding: "7px 14px", color: screen === s.id ? "white" : "#777", fontSize: px(12), fontWeight: 700, cursor: "pointer", fontFamily: "inherit", transition: "all 0.2s" }}>{s.l}</button>
          ))}
        </div>
      </div>
    </div>
  );
}
